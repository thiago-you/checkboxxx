# Checkboxxx
Checkbox that has three states to toggle: Active, Inactive and Indeterminate. The library provides methods for configuring indeterminate state toggle.

### SAMPLE
    // sample list

### USAGE DEMO
    // init checkboxxx


### Requirements
    Min SDK Version >= 19

### Import library from Jitpack
    - Add Jitpack repository into you project (build.gradle):

        allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
        }

    - Add library implementation into build.gradle (Module:app)

        dependencies {
            ...
            implementation 'com.github.thiago-you:checkboxxx:Tag'
        }

    - Sync build.gradle and build your project

See [Jitpack](https://jitpack.io/docs/) docs for more info.

### Download Library
Follow these steps to import the library into your project:

    - Download the library
    - Go to you project under "File" -> "New" -> "Import Module"
    - In build.gradle, import library as "implementation project(':checkboxxx')"
    - Sync build.gradle and build your project