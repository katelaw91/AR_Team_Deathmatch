# Creating Stickers with Pet Segmentation

[ ![Codeship Status for fritzlabs/fritz-sdk-android](https://app.codeship.com/projects/c74152e0-65d1-0136-2d69-32e87736c6c6/status?branch=master)](https://app.codeship.com/projects/297281)
[![Twitter](https://img.shields.io/badge/twitter-@fritzlabs-blue.svg?style=flat)](http://twitter.com/fritzlabs)

In this app, the user can automatically create a sticker of their pet and save it in their camera roll.

This example app uses the on-device Pet Segmentation API for Android.

- [Overview](https://www.fritz.ai/features/image-segmentation.html)
- [Documentation](https://docs.fritz.ai/develop/vision/image-segmentation/android.html)

## Fritz AI

Fritz AI is the machine learning platform for iOS and Android developers. Teach your mobile apps to see, hear, sense, and think. Start with our ready-to-use feature APIs or connect and deploy your own custom models.

## Requirements

- Android Studio 3.2 or above
- Android device in developer model (USB debugging enabled)

## Getting Started

**Step 1: Create a Fritz AI Account**

[Sign up](https://app.fritz.ai/register?utm_source=github&utm_campaign=fritz-examples) for a free account on Fritz AI in order to get started.

**Step 2: Clone / Fork the fritz-examples repository and open the BackgroundReplacementApp app in Android Studio**

```
git clone https://github.com/fritzlabs/fritz-examples.git
```

In Android Studio, choose "Open an existing Android Studio project" and select `PetStickerApp`.

**Step 3: Build the Android Studio Project**

Select "Build > Make Project" from the top nav. Download any missing libraries if applicable. This should sync the gradle dependencies so give the build a second to complete.

**Step 4: Install the app onto your device**

With your Android device connected, select `Run > Run App` from the top nav. When running the app for the first time, you'll have to give permissions to access the camera. After the app is installed and running, take a picture of a pet and you'll see a preview of the sticker. You can then save the sticker to your photo gallery.

## Official Documentation

[SDK Documentation](https://docs.fritz.ai/?utm_source=github&utm_campaign=fritz-examples)

[Android API Docs](https://docs.fritz.ai/android/latest/index.html?utm_source=github&utm_campaign=fritz-examples)

## Join the community

[Heartbeat](https://heartbeat.fritz.ai/?utm_source=github&utm_campaign=fritz-examples) is a community of developers interested in the intersection of mobile and machine learning. [Chat with us in Slack](https://www.fritz.ai/slack?utm_source=github&utm_campaign=fritz-examples) and stay up to date on the latest mobile ML news with our [Newsletter](https://mobileml.us16.list-manage.com/subscribe?u=de53bead690affb8e9a21de8f&id=68acb5c0fd).

## Help

For any questions or issues, you can:

- Submit an issue on this repo
- Go to our [Help Center](https://docs.fritz.ai/help-center/index.html?utm_source=github&utm_campaign=fritz-examples)
- Message us directly in [Slack](https://www.fritz.ai/slack?utm_source=github&utm_campaign=fritz-examples)
