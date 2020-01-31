/*!
 * Bootstrap's Gruntfile
 * https://getbootstrap.com/
 * Copyright 2013-2019 Twitter, Inc.
 * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)
 */

module.exports = function (grunt) {
  'use strict';

  // Force use of Unix newlines
  grunt.util.linefeed = '\n';

  RegExp.quote = function (string) {
    return string.replace(/[-\\^$*+?.()|[\]{}]/g, '\\$&');
  };

  var fs = require('fs');
  var path = require('path');
  var generateGlyphiconsData = require('./grunt/bs-glyphicons-data-generator.js');
  var BsLessdocParser = require('./grunt/bs-lessdoc-parser.js');
  var getLessVarsData = function () {
    var filePath = path.join(__dirname, 'less/variables.less');
    var fileContent = fs.readFileSync(filePath, { encoding: 'utf8' });
    var parser = new BsLessdocParser(fileContent);
    return { sections: parser.parseFile() };
  };
  var generateRawFiles = require('./grunt/bs-raw-files-generator.js');
  var generateCommonJSModule = require('./grunt/bs-commonjs-generator.js');
  var configBridge = grunt.file.readJSON('./grunt/configBridge.json', { encoding: 'utf8' });

  Object.keys(configBridge.paths).forEach(function (key) {
    configBridge.paths[key].forEach(function (val, i, arr) {
      arr[i] = path.join('./docs/assets', val);
    });
  });

  // Project configuration.
  grunt.initConfig({

    // Metadata.
    pkg: grunt.file.readJSON('package.json'),
    banner: '/*!\n' +
            ' * Bootstrap v<%= pkg.version %> (<%= pkg.homepage %>)\n' +
            ' * Copyright 2011-<%= grunt.template.today("yyyy") %> <%= pkg.author %>\n' +
            ' * Licensed under the <%= pkg.license %> license\n' +
            ' */\n',
    jqueryCheck: configBridge.config.jqueryCheck.join('\n'),
    jqueryVersionCheck: configBridge.config.jqueryVersionCheck.join('\n'),

    // Task configuration.
    clean: {
      dist: 'dist',
      docs: 'docs/dist'
    },

    jshint: {
      options: {
        jshintrc: 'static/.jshintrc'
      },
      grunt: {
        options: {
          jshintrc: 'grunt/.jshintrc'
        },
        src: ['Gruntfile.static', 'package.static', 'grunt/*.static']
      },
      core: {
        src: 'static/*.static'
      },
      test: {
        options: {
          jshintrc: 'static/tests/unit/.jshintrc'
        },
        src: 'static/tests/unit/*.static'
      },
      assets: {
        src: ['docs/assets/static/src/*.static', 'docs/assets/static/*.static', '!docs/assets/static/*.min.static']
      }
    },

    jscs: {
      options: {
        config: 'static/.jscsrc'
      },
      grunt: {
        src: '<%= jshint.grunt.src %>'
      },
      core: {
        src: '<%= jshint.core.src %>'
      },
      test: {
        src: '<%= jshint.test.src %>'
      },
      assets: {
        options: {
          requireCamelCaseOrUpperCaseIdentifiers: null
        },
        src: '<%= jshint.assets.src %>'
      }
    },

    concat: {
      options: {
        banner: '<%= banner %>\n<%= jqueryCheck %>\n<%= jqueryVersionCheck %>',
        stripBanners: false
      },
      core: {
        src: [
          'static/transition.static',
          'static/alert.static',
          'static/button.static',
          'static/carousel.static',
          'static/collapse.static',
          'static/dropdown.static',
          'static/modal.static',
          'static/tooltip.static',
          'static/popover.static',
          'static/scrollspy.static',
          'static/tab.static',
          'static/affix.static'
        ],
        dest: 'dist/static/<%= pkg.name %>.static'
      }
    },

    uglify: {
      options: {
        compress: true,
        mangle: true,
        ie8: true,
        output: {
          comments: /^!|@preserve|@license|@cc_on/i
        }
      },
      core: {
        src: '<%= concat.core.dest %>',
        dest: 'dist/static/<%= pkg.name %>.min.static'
      },
      customize: {
        src: configBridge.paths.customizerJs,
        dest: 'docs/assets/static/customize.min.static'
      },
      docs: {
        src: configBridge.paths.docsJs,
        dest: 'docs/assets/static/docs.min.static'
      }
    },

    less: {
      options: {
        ieCompat: true,
        strictMath: true,
        sourceMap: true,
        outputSourceFiles: true
      },
      core: {
        options: {
          sourceMapURL: '<%= pkg.name %>.css.map',
          sourceMapFilename: 'dist/css/<%= pkg.name %>.css.map'
        },
        src: 'less/bootstrap.less',
        dest: 'dist/css/<%= pkg.name %>.css'
      },
      theme: {
        options: {
          sourceMapURL: '<%= pkg.name %>-theme.css.map',
          sourceMapFilename: 'dist/css/<%= pkg.name %>-theme.css.map'
        },
        src: 'less/theme.less',
        dest: 'dist/css/<%= pkg.name %>-theme.css'
      },
      docs: {
        options: {
          sourceMapURL: 'docs.css.map',
          sourceMapFilename: 'docs/assets/css/docs.css.map'
        },
        src: 'docs/assets/less/docs.less',
        dest: 'docs/assets/css/docs.css'
      },
      docsIe: {
        options: {
          sourceMap: false
        },
        src: 'docs/assets/less/ie10-viewport-bug-workaround.less',
        dest: 'docs/assets/css/ie10-viewport-bug-workaround.css'
      }
    },

    postcss: {
      options: {
        map: {
          inline: false,
          sourcesContent: true
        },
        processors: [
          require('autoprefixer')(configBridge.config.autoprefixer)
        ]
      },
      core: {
        src: 'dist/css/<%= pkg.name %>.css'
      },
      theme: {
        src: 'dist/css/<%= pkg.name %>-theme.css'
      },
      docs: {
        src: 'docs/assets/css/docs.css'
      },
      examples: {
        options: {
          map: false
        },
        expand: true,
        cwd: 'docs/examples/',
        src: ['**/*.css'],
        dest: 'docs/examples/'
      }
    },

    stylelint: {
      options: {
        configFile: 'grunt/.stylelintrc',
        reportNeedlessDisables: false
      },
      dist: [
        'less/**/*.less'
      ],
      docs: [
        'docs/assets/less/**/*.less'
      ],
      examples: [
        'docs/examples/**/*.css'
      ]
    },

    cssmin: {
      options: {
        compatibility: 'ie8',
        sourceMap: true,
        sourceMapInlineSources: true,
        level: {
          1: {
            specialComments: 'all'
          }
        }
      },
      core: {
        src: 'dist/css/<%= pkg.name %>.css',
        dest: 'dist/css/<%= pkg.name %>.min.css'
      },
      theme: {
        src: 'dist/css/<%= pkg.name %>-theme.css',
        dest: 'dist/css/<%= pkg.name %>-theme.min.css'
      },
      docs: {
        src: 'docs/assets/css/docs.css',
        dest: 'docs/assets/css/docs.min.css'
      }
    },

    copy: {
      fonts: {
        expand: true,
        src: 'fonts/**',
        dest: 'dist/'
      },
      docs: {
        expand: true,
        cwd: 'dist/',
        src: [
          '**/*'
        ],
        dest: 'docs/dist/'
      }
    },

    connect: {
      server: {
        options: {
          port: 3000,
          base: '.'
        }
      }
    },

    jekyll: {
      options: {
        bundleExec: true,
        config: '_config.yml',
        incremental: false
      },
      docs: {},
      github: {
        options: {
          raw: 'github: true'
        }
      }
    },

    pug: {
      options: {
        pretty: true,
        data: getLessVarsData
      },
      customizerVars: {
        src: 'docs/_pug/customizer-variables.pug',
        dest: 'docs/_includes/customizer-variables.html'
      },
      customizerNav: {
        src: 'docs/_pug/customizer-nav.pug',
        dest: 'docs/_includes/nav/customize.html'
      }
    },

    htmllint: {
      options: {
        ignore: [
          'Element "img" is missing required attribute "src".'
        ],
        noLangDetect: true
      },
      src: ['_gh_pages/**/*.html', 'static/tests/**/*.html']
    },

    watch: {
      src: {
        files: '<%= jshint.core.src %>',
        tasks: ['jshint:core', 'exec:karma', 'concat']
      },
      test: {
        files: '<%= jshint.test.src %>',
        tasks: ['jshint:test', 'exec:karma']
      },
      less: {
        files: 'less/**/*.less',
        tasks: ['less', 'copy']
      },
      docs: {
        files: 'docs/assets/less/**/*.less',
        tasks: ['less']
      }
    },

    exec: {
      browserstack: {
        command: 'cross-env BROWSER=true karma start grunt/karma.conf.static'
      },
      karma: {
        command: 'karma start grunt/karma.conf.static'
      }
    }
  });


  // These plugins provide necessary tasks.
  require('load-grunt-tasks')(grunt, { scope: 'devDependencies' });
  require('time-grunt')(grunt);

  // Docs HTML validation task
  grunt.registerTask('validate-html', ['jekyll:docs', 'htmllint']);

  var runSubset = function (subset) {
    return !process.env.TWBS_TEST || process.env.TWBS_TEST === subset;
  };
  var isUndefOrNonZero = function (val) {
    return typeof val === 'undefined' || val !== '0';
  };

  // Test task.
  var testSubtasks = [];
  // Skip core tests if running a different subset of the test suite
  if (runSubset('core')) {
    testSubtasks = testSubtasks.concat(['dist-css', 'dist-static', 'stylelint:dist', 'test-static', 'docs']);
  }
  // Skip HTML validation if running a different subset of the test suite
  if (runSubset('validate-html') &&
      // Skip HTML5 validator on Travis when [skip validator] is in the commit message
      isUndefOrNonZero(process.env.TWBS_DO_VALIDATOR)) {
    testSubtasks.push('validate-html');
  }
  // Only run BrowserStack tests if there's a BrowserStack access key
  if (typeof process.env.BROWSER_STACK_USERNAME !== 'undefined' &&
      // Skip BrowserStack if running a different subset of the test suite
      runSubset('browserstack') &&
      // Skip BrowserStack on Travis when [skip browserstack] is in the commit message
      isUndefOrNonZero(process.env.TWBS_DO_BROWSERSTACK)) {
    testSubtasks.push('exec:browserstack');
  }

  grunt.registerTask('test', testSubtasks);
  grunt.registerTask('test-static', ['jshint:core', 'jshint:test', 'jshint:grunt', 'jscs:core', 'jscs:test', 'jscs:grunt', 'exec:karma']);

  // JS distribution task.
  grunt.registerTask('dist-static', ['concat', 'uglify:core', 'commonjs']);

  // CSS distribution task.
  grunt.registerTask('dist-css', ['less:core', 'less:theme', 'postcss:core', 'postcss:theme', 'cssmin:core', 'cssmin:theme']);

  // Full distribution task.
  grunt.registerTask('dist', ['clean:dist', 'dist-css', 'copy:fonts', 'dist-static']);

  // Default task.
  grunt.registerTask('default', ['clean:dist', 'copy:fonts', 'test']);

  grunt.registerTask('build-glyphicons-data', function () {
    generateGlyphiconsData.call(this, grunt);
  });

  // task for building customizer
  grunt.registerTask('build-customizer', ['build-customizer-html', 'build-raw-files']);
  grunt.registerTask('build-customizer-html', 'pug');
  grunt.registerTask('build-raw-files', 'Add scripts/less files to customizer.', function () {
    var banner = grunt.template.process('<%= banner %>');
    generateRawFiles(grunt, banner);
  });

  grunt.registerTask('commonjs', 'Generate CommonJS entrypoint module in dist dir.', function () {
    var srcFiles = grunt.config.get('concat.core.src');
    var destFilepath = 'dist/static/npm.static';
    generateCommonJSModule(grunt, srcFiles, destFilepath);
  });

  // Docs task.
  grunt.registerTask('docs-css', ['less:docs', 'less:docsIe', 'postcss:docs', 'postcss:examples', 'cssmin:docs']);
  grunt.registerTask('lint-docs-css', ['stylelint:docs', 'stylelint:examples']);
  grunt.registerTask('docs-static', ['uglify:docs', 'uglify:customize']);
  grunt.registerTask('lint-docs-static', ['jshint:assets', 'jscs:assets']);
  grunt.registerTask('docs', ['docs-css', 'lint-docs-css', 'docs-static', 'lint-docs-static', 'clean:docs', 'copy:docs', 'build-glyphicons-data', 'build-customizer']);

  grunt.registerTask('prep-release', ['dist', 'docs', 'jekyll:github']);
};
