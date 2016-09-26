# AutoLinkTextView

AutoLinkTextView is TextView drop-in replacement supporting Hashtags (#), Mentions (@) , URLs (http://), Phone and Email with click handling

![](screens/gif1.gif)

The current minSDK version is API level 14 Android 4.0 (ICE CREAM SANDWICH).

## Features

* Default support for **Hashtag, Mention, Link, Phone number and Email**
* Support for **custom types** via regex
* Ability to set type foreground color
* Ability to enable highlighting only for the desired types

![](screens/screen1.png)

#Download
-----------------------

Gradle:
coming soon

## Setup and usage

Add AutoLinkTextView to your layout
```xml
    <com.luseen.autolinklibrary.AutoLinkTextView
         android:id="@+id/active"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content" />
```

```java
AutoLinkTextView autoLinkTextView = (AutoLinkTextView) findViewById(R.id.active);
```

Set up mode or modes
```java
autoLinkTextView.setAutoLinkMode(
                AutoLinkMode.MODE_PHONE);
```

Set text to AutoLinkTextView
```java
autoLinkTextView.setAutoLinkText(getString(R.string.long_text));
```

Set AutoLinkTextView click listener
```java
autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {

            }
        });
```

Customizing
---------

AutoLinkModes
-------------------------
AutoLinkMode.MODE_PHONE
#![](screens/screen2.png)
-------------------------
AutoLinkMode.MODE_HASHTAG
#![](screens/screen3.png)
-------------------------
AutoLinkMode.MODE_URL
#![](screens/screen4.png)
-------------------------
AutoLinkMode.MODE_MENTION
#![](screens/screen5.png)
-------------------------
AutoLinkMode.MODE_EMAIL
#![](screens/screen6.png)
-------------------------
AutoLinkMode.MODE_CUSTOM
#![](screens/screen7.png)
if you use custom mode, you should also add custom regex,
```java
autoLinkTextView.addCustomRegex("\\sGoogle\\b");
```
Note:Otherwise ```MODE_CUSTOM``` will return ```MODE_URL```
-------------------------
You can also use multiple types
```java
autoLinkTextView.setAutoLinkMode(
                AutoLinkMode.MODE_MENTION,
                AutoLinkMode.MODE_PHONE);
```
![](screens/screen1.png)
-------------------------
You can also change text color for autoLink mode
```java
autoLinkTextView.setHashtagModeColor(ContextCompat.getColor(this, R.color.yourColor));
autoLinkTextView.setPhoneModeColor(ContextCompat.getColor(this, R.color.yourColor));
autoLinkTextView.setCustomModeColor(ContextCompat.getColor(this, R.color.yourColor));
autoLinkTextView.setUrlModeColor(ContextCompat.getColor(this, R.color.yourColor));
autoLinkTextView.setMentionModeColor(ContextCompat.getColor(this, R.color.yourColor));
autoLinkTextView.setEmailModeColor(ContextCompat.getColor(this, R.color.yourColor));
```
-------------------------
And also autoLink text pressed state color
```java
autoLinkTextView.setSelectedStateColor(ContextCompat.getColor(this, R.color.yourColor));
```
-------------------------
## Contact

Pull requests are more than welcome.
Please fell free to contact me if there is any problem when using the library.

- **Email**: armcha01@gmail.com
- **Facebook**: https://web.facebook.com/chatikyana
- **Google +**: https://plus.google.com/112011638040018774140
- **Website**: http://luseen.com/

License
--------


      Space Navigation library for Android
      Copyright (c) 2016 Arman Chatikyan (https://github.com/armcha/Space-Navigation-View).

      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.




