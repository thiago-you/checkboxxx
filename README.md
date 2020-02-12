# Checkboxxx
Checkbox that has three states to toggle: Active, Inactive and Indeterminate. The library provides methods for configuring indeterminate state toggle.

### DEMO
![checkboxxx](sample/checkboxxx.gif?raw=true "Checkboxxx")

### USAGE DEMO
    // on XML layout
    <you.thiago.checkboxxx.CheckBoxxx
            android:id="@+id/checkboxxx"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Your checkboxxx"
            android:textColor="@color/colorPrimary"
            android:textSize="18sp" />

    // on code you can allow indeterminate state
    checkboxxx.allowIndeterminateState(true);

    // get checked state
    // -1 => Indeterminate
    // 0 => Unchecked
    // 1 => Checked
    checkboxxx.getState();

    // you can set state using class constants
    checkboxxx.getState(CheckBoxxx.CHECKED);

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