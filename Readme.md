<pre>
1) TestDroid (Bitbar)

- Used via TestDroid Gradle plugin

https://github.com/bitbar/testdroid-gradle-plugin

 - Create a free account
 - Create a project
 - Create a device group (Note that the default groups might not work)
 - Configure Gradle plugin based on the created settings (account, password, project, device group etc).

2) AWS Device Farm (Amazon)

- Used via AWS Device Farm Gradle Plugin

https://github.com/awslabs/aws-device-farm-gradle-plugin

- Billing needs to be enabled (first 250 minutes are free)
- Create a project
- Generating a proper IAM user:

	https://github.com/awslabs/aws-device-farm-gradle-plugin

	Log into your AWS web console UI.
	Click "Identity & Access Management".
	On the left-hand side of the screen, click "Users".
	Click "Create New Users".
	Enter a user name of your choice.
	Leave the "Generate an access key for each user" checkbox checked.
	Click "Create".
	View or optionally download the User security credentials that were created; you will them them later.
	Click "Close" to return to the IAM screen.
	Click your user name in the list.
	Select permissions tab
	Attach the policy: AWSDeviceFarmFullAccess (this is the only policy related to device farm)

- Create a device pool (or use the default "Top Devices")

	http://docs.aws.amazon.com/devicefarm/latest/developerguide/how-to-create-device-pool.html

    Make sure you have started to create a run and stopped on the Select devices page in the Create a new run wizard.
	On the Select devices page, choose Create new device pool.
	For Name, type a name that will make this device pool easy to identify in the future.
	For Description, type a description that will make this device pool easy to identify in the future.

- Configure Gradle plugin based on the created settings (access key, secret key, project, device group etc).
    
3) Firebase Test Lab for Android (Google)

 - Used via gcloud command-line client

    https://firebase.google.com/docs/test-lab/command-line

 - Billing needs to be enabled. A Firebase "Blaze" plan is required to use Test Lab.

 - If not present, install the Google Cloud SDK on your local system.
 - Make sure your Cloud SDK installation is up to date and includes the gcloud beta commands:

	gcloud components update beta

 - Set your current gcloud project to your project ID:

	gcloud config set project <YOUR-PROJECT-ID>

 - Make sure your authentication credentials are current:

	gcloud auth login

- Build the APK's

- Run the test script ./google-firebase.demo.sh 

Comparison:

 1) Firebase

 	+ Running through the gcloud CLI gives you the ability to wait for tests to complete

 	+ Easy to read summary of the current "test matrix"

 	+ Easy to read separate test results

 	+ Video recording for each test

 	- You have to pay to try it (although its cheap)

 	- No CPU usage (?)

 	- No memory usage (?)

 	- Extremely low number of devices and API levels available

 2) TestDroid

 	+ Greatest selection of devices (low as API 10, 2.3.3, two devices)

 	+ Can try it for free, easy to setup

 	+ Easy to use Gradle plugin

 	+ CPU and memory usage

 	+ Easy to read summary of the current "test summary"

 	+ What looks like a powerful API

 	- Extremely bad web interface for test failure logs. Hard to read with several failures

 	- Individual test failures cannot be separated. Screenshots can be hard to match to the failure.

 	- No video available (TestDroid say they can enable it for paying customers)

 	- Gradle plugin does not have the option to wait for tests to complete and print results on commandline

 3) Amazon

 	+ Second best selection of devices (low as API 17, 4.2.2)

 	+ Can try it for free (first 250 minutes of device time), easy to setup

 	+ Easy to use Gradle plugin

 	+ Easy to read summary of the current "test run"

 	+ What looks like a powerful API

 	+ Best pricing model (250 / month per device slot, concurrency meaning how many devices can be tested at a time, no hourly limit etc)

	- Gradle plugin also does not have the option to wait for tests to complete and print results on commandline

	- Video FPS is low, can be difficult to tell what the problem actually was

	Other:

	CPU and memory usage sometimes seems to be reporting incorrect values

 4) All of them

 	- No provider seems to have a working implementation for generating summaries from multiple weeks etc. TestDroid is the only one that theoretically supports this.

Pricing:

 1) Firebase:

    https://firebase.google.com/pricing/
    
    - Device hours $5/physical, $1/virtual
    
    - Meaning 60 hours / 300$

 2) TestDroid

    http://bitbar.com/testing/pricing/public-cloud/	
    
    - 10 hours at $ 99 / month with $0.17 per Extra Minute
    
    - 60 hours at $ 499 / month with $0.15 per Extra Minute
    
    - 250 hours at $ 1799 / month with $0.13 per Extra Minute
    
    - Custom - Ask for Quote
        
 3) Amazon
    
    https://aws.amazon.com/device-farm/pricing/ 	
    
    - Pay as you go at $0.17/minute
    
    - Unlimited test & access at $250/device slot/month
    
    Device slots correspond to concurrency. For instance, if you purchase ten automated test Android device slots and schedule a run on 100 Android devices, Device Farm will execute your tests on up to ten devices at a time until all tests are completed on your selected devices.

</pre>

