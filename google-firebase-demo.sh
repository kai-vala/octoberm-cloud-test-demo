# Requires you to have the gcloud sdk installed (with beta tools)
# https://firebase.google.com/docs/test-lab/command-line
# Also check that the paths are correct.
gcloud beta test android run \
      --type instrumentation \
      --app app/build/outputs/apk/app-debug.apk \
      --test app/build/outputs/apk/app-debug-androidTest.apk \
      --device-ids hammerhead \
      --os-version-ids 22 \
      --locales en \
      --orientations portrait
