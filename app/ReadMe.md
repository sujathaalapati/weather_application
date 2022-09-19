# Introduction 
The goal of this project is to provide the weather information for the current date and coming 15 days also


Android Studio as your main IDE
The recommended IDE for Android development is Android Studio because it is developed and constantly updated by Google, has good support for Gradle, contains a range of useful monitoring and analysis tools and is fully tailored for Android development.

Avoid adding Android Studio's specific configuration files, such as .iml files to the version control system as these often contain configurations specific of your local machine, which won't work for your colleagues.

Libraries

Gson is another popular choice and being a smaller library than Jackson, you might prefer it to avoid 65k methods limitation. Also, if you are using

Networking, caching, and images. There are a couple of battle-proven solutions for performing requests to backend servers, which you should use rather than implementing your own client. We recommend basing your stack around OkHttp for efficient HTTP requests and using Retrofit to provide a typesafe layer. If you choose Retrofit, consider Picasso for loading and caching images.

Retrofit, Picasso and OkHttp are created by the same company, so they complement each other nicely and compatability issues are uncommon.

Glide is another option for loading and caching images. It has support for animated GIFs, circular images and claims of better performance than Picasso, but also a bigger method count.

Permissions
On Android versions prior to Android 6.0, wallabag requires the following permissions:

Full Network Access.
View Network Connections.
