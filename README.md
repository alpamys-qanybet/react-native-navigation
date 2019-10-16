## To make it work install from master:
```yarn add alpamys-qanybet/react-native-navigation#master```

and follow the instructions from old V1 [docs](https://github.com/wix/react-native-navigation/tree/v1/docs)

## Important!
you should change your ```package.json```:

```
"dependencies": {
  ...
  "react-native-navigation": "https://github.com/alpamys-qanybet/react-native-navigation/tarball/master",
},

```

or use specific commit
more about it [here](http://debuggable.com/posts/how-to-fork-patch-npm-modules:4e2eb9f3-e584-44be-b1a9-3db7cbdd56cb)

<h1 align="center">
  <img src="./logo.png"/><br>
  React Native Navigation
</h1>

[![NPM Version](https://img.shields.io/npm/v/react-native-navigation.svg?style=flat)](https://www.npmjs.com/package/react-native-navigation)
[![NPM Downloads](https://img.shields.io/npm/dm/react-native-navigation.svg?style=flat)](https://www.npmjs.com/package/react-native-navigation)
[![Build Status](https://img.shields.io/jenkins/s/http/jenkins-oss.wixpress.com:8080/job/react-native-navigation-master.svg)](https://jenkins-oss.wixpress.com/job/react-native-navigation-master/)
[![Join us on Discord](https://img.shields.io/badge/discord-react--native--navigation-738bd7.svg?style=flat)](https://discord.gg/DhkZjq2)

React Native Navigation provides 100% native platform navigation on both iOS and Android for React Native apps. The JavaScript API is simple and cross-platform - just install it in your app and give your users the native feel they deserve. Using redux? No problem: React Native Navigation comes with optional redux support out of the box. Ready to get started? Check out the [docs](https://github.com/wix/react-native-navigation/tree/v1/docs).

### Real world examples

<img src="https://github.com/wix/react-native/blob/master/src/videos/demo.gif?raw=true" width="240">&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/wix/react-native/blob/master/src/videos/rnn-example-demo.gif?raw=true" width="240">

On the left - The Wix app.

On the right - The example app.


## Quick Links
* [Stack Overflow](http://stackoverflow.com/questions/tagged/react-native-navigation)
* [Chat with us](https://discord.gg/DhkZjq2)
----

One of the major things missing from React Native core is fully featured native navigation. Navigation includes the entire skeleton of your app with critical components like nav bars, tab bars and side menu drawers.

If you're trying to deliver a user experience that's on par with the best native apps out there, you simply can't compromise on JS-based components trying to fake the real thing.

For example, this package replaces the native [NavigatorIOS](https://facebook.github.io/react-native/docs/navigatorios.html) that has been [abandoned](https://facebook.github.io/react-native/docs/navigator-comparison.html) in favor of JS-based solutions that are easier to maintain. For more details see in-depth discussion [here](https://github.com/wix/react-native-controllers#why-do-we-need-this-package).
--

## License

The MIT License.

See [LICENSE](LICENSE)
