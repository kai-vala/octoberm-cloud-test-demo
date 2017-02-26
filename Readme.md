# Introduction 
This repository currently serves as a quick start example for how to implement Cloud Testing for [Amazon Device farm](https://aws.amazon.com/device-farm/), [TestDroid](https://cloud.testdroid.com/) and [Firebase Test Labs](https://firebase.google.com/docs/test-lab/)

It uses the Gradle plugins for Amazon and TestDroid and the Google Cloud SDK for Firebase Test Labs.

This was originally made for the [October madness Helsinki Android meetup](https://www.meetup.com/Helsinki-Android-Meetup/events/234749181/) held at the Futurice offices.

# Running the project

- Install [Android Studio](https://developer.android.com/studio/index.html)
- Clone the repository

    git clone https://github.com/kai-vala/octoberm-cloud-test-demo.git

- Open the cloned project directory in Android studio and try to build or sync with Gradle.
- Android Studio should guide you with the installation / upgrade to Gradle, Android SDK and other dependencies.
- **Highly recommended that you check the hotkeys for Android Studio / IntelliJ (Settings -> Keymap)!**

-  Use Navigate -> File to open a file quickly e.g. `VerifyTabNavigationTest`

## Implementing UI test

- UI tests can be found under the `androidTest` package.
- Full path being: `src/androidTest/java/com/octoberm/cloudtestdemo/test`
- See the examples under the `testcases` package for the `AndroidJUnit4` syntax for implementing the test files (`@Rule`, `@Test` etc).

## Running tests in Android Studio

- You can run UI tests directly from Android Studio simply from the right click menu e.g. Run 'VerifyTabNavigationTest'

## Running tests via Gradle

Gradle tasks can be executed with the the `gradlew` helper. Use the `tasks` command to list all available tasks for the project

The relevant tasks listed here are the `testdroidUpload` and `devicefarmUpload`

```
./gradlew tasks

...

Verification tasks
------------------
check - Runs all checks.
connectedAndroidTest - Installs and runs instrumentation tests for all flavors on connected devices.
connectedCheck - Runs all device checks on currently connected devices.
connectedDebugAndroidTest - Installs and runs the tests for debug on connected devices.
deviceAndroidTest - Installs and runs instrumentation tests using all Device Providers.
deviceCheck - Runs all device checks using Device Providers and Test Servers.
devicefarmUpload - Uploads APKs for Build 'debugAndroidTest' to Test Server 'Devicefarm'.
lint - Runs lint on all variants.
lintDebug - Runs lint on the Debug build.
lintRelease - Runs lint on the Release build.
test - Run unit tests for all variants.
testDebugUnitTest - Run unit tests for the debug build.
testdroidUpload - Uploads APKs for Build 'debugAndroidTest' to Test Server 'Testdroid'.
testReleaseUnitTest - Run unit tests for the release build.
```

## Configuring credentials for CI environments

The simples way to achieve this is to configure environment variables in your CI and call them in the Gradle configuration. For [Travis CI](https://docs.travis-ci.com/user/environment-variables/) you should configure the variables in the web config and not your build script.

# Running tests with Cloud services

TestDroid and Amazon Gradle plugins can both be configured from the `build.gradle` file in their relevant sections.

## TestDroid (Bitbar)

Used via [TestDroid Gradle plugin](https://github.com/bitbar/testdroid-gradle-plugin)

- Create a free account
- Create a project
- Create a device group (Note that the default groups might not work)
- Configure Gradle plugin based on the created settings (API Key, project, device group etc).

## AWS Device Farm (Amazon)

Used via [AWS Device Farm Gradle Plugin](https://github.com/awslabs/aws-device-farm-gradle-plugin)

- Billing needs to be enabled (first 250 minutes are free)
- Create a project and a user with Device Farm access

Generating a proper IAM user:

- Log into your AWS web console UI.
- Click "Identity & Access Management".
- On the left-hand side of the screen, click "Users".
- Click "Create New Users".
- Enter a user name of your choice.
- Leave the "Generate an access key for each user" checkbox checked.
- Click "Create".
- View or optionally download the User security credentials that were created; you will need for 
the Gradle plugin
- Click "Close" to return to the IAM screen.
- Click your user name in the list.
- Select permissions tab
- Attach the policy: AWSDeviceFarmFullAccess (this is the only policy related to device farm)

(For authentication errors, first try generating a new access key)

- [Create a device pool or use the default "Top Devices"](http://docs.aws.amazon.com/devicefarm/latest/developerguide/how-to-create-device-pool.html)
- Make sure you have started to create a run and stopped on the Select devices page in the Create 
a new run wizard.
- On the Select devices page, choose Create new device pool.
- For Name, type a name that will make this device pool easy to identify in the future.
- For Description, type a description that will make this device pool easy to identify in the 
future.
- Finally configure Gradle plugin based on the created settings (access key, secret key, project, 
device group etc).
    
## Firebase Test Lab for Android (Google)

Used via the [gcloud command-line client](https://firebase.google.com/docs/test-lab/command-line)

- Free to try with the new [15 tests/day option](https://firebase.google.com/pricing/)
- If not present, install the [Google Cloud SDK](https://cloud.google.com/sdk/) on your local system.
- Make sure your Cloud SDK installation is up to date and includes the gcloud beta commands:

    gcloud components update beta

- Set your current gcloud project to your project ID:

    gcloud beta projects list

    gcloud config set project <YOUR-PROJECT-ID>

- Make sure your authentication credentials are current:

    gcloud auth login

- Build the APK's
- Run the test script ./google-firebase.demo.sh 

## Quick comparison of services:

### Firebase

Good:

- Running through the gcloud CLI gives you the ability to wait for tests to complete
- Easy to read summary of the current "test matrix"
- Easy to read separate test results
- Video recording for each test

Bad:

- No CPU usage (?)
- No memory usage (?)
- Extremely low number of different devices and API levels available

### TestDroid

Note that TestDroid also offers custom cloud solutions (e.g. on site hosting / setup services)

Good:

- Greatest selection of devices (low as API 10, 2.3.3, two devices)
- Can try it for free, easy to setup
- Easy to use Gradle plugin
- CPU and memory usage
- Easy to read summary of the current "test summary"
- What looks like a powerful API

Bad:

- Extremely bad web interface for test failure logs. Hard to read with several failures
- Individual test failures cannot be separated. Screenshots can be hard to match to the failure.
- No video available (TestDroid say they can enable it for paying customers)
- Gradle plugin does not have the option to wait for tests to complete and print results on commandline

### Amazon Device Farm

Good:

- Second best selection of devices (low as API 17, 4.2.2)
- Can try it for free (first 250 minutes of device time), easy to setup
- Easy to use Gradle plugin
- Easy to read summary of the current "test run"
- What looks like a powerful API
- Best pricing model (250 / month per device slot, concurrency meaning how many devices can be tested at a time, no hourly limit etc)

Bad:

- Gradle plugin also does not have the option to wait for tests to complete and print results on commandline
- Video FPS is low, can be difficult to tell what the problem actually was
- CPU and memory usage sometimes seems to be reporting incorrect values

### All of them

- No provider seems to have a working implementation for generating summaries from multiple weeks etc. TestDroid is the only one that theoretically supports this.

### [Firebase pricing](https://firebase.google.com/pricing/)

- Device hours $5/physical, $1/virtual
- Meaning 60 hours / 300$

### [TestDroid pricing](http://bitbar.com/testing/pricing/public-cloud/)

Note that TestDroid also offers custom cloud solutions (e.g. on site hosting / setup services)

- 10 hours at $ 99 / month with $0.17 per Extra Minute
- 60 hours at $ 499 / month with $0.15 per Extra Minute
- 250 hours at $ 1799 / month with $0.13 per Extra Minute
- Custom - Ask for Quote
    
        
### [Amazon Device Farm pricing](https://aws.amazon.com/device-farm/pricing/ )
    
- Pay as you go at $0.17/minute
- Unlimited test & access at $250/device slot/month
    
Device slots correspond to concurrency. For instance, if you purchase one slot and you pool has 10 devices your tests on one device at a time until they have been executed on all devices.
