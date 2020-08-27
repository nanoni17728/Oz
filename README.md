Oz
========================
Oz is a small [Clojurescript] codebase that compiles down to a web app
to apply photographic filters.

Build & Requirements
------------------------
To build the application you'll need to get:
* A [JVM] (both Oracle's and OpenJDK works)
* [Node.js] (the current LTS − 12.18.3 − will do)
* The [Yarn] package manager

It should work on every major OS but make sure all the tools are in your PATH. If you're able to autocomplete `java` and `yarn` from your terminal you should be good to go.

This codebase uses [shadow-cljs] as its main build tool. Clone this repository, run `yarn install` to fetch the dependencies then `yarn shadow-cljs release app` and you're good to go!

All the files needed to be served by your http server are in the `public/` folder.

License & Contributing
------------------------
Copyright © 2020 Nanoni

Oz is published under the GNU Affero General Public License. For more informations, see the [LICENSE](LICENSE) file or a more readable summary at [choosealicense.com](https://choosealicense.com/licenses/agpl-3.0/)

This is a small prototype and as such I may not accept any pull request but feel free to fork away.


[Clojurescript]: https://clojurescript.org/
[JVM]: https://openjdk.java.net/install/index.html
[Node.js]: https://nodejs.org/en/download/
[shadow-cljs]: https://shadow-cljs.org/
[Yarn]: https://yarnpkg.com/
