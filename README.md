![FolioReader logo](https://raw.githubusercontent.com/FolioReader/FolioReaderKit/assets/folioreader.png)

[![Build Status](https://api.travis-ci.org/FolioReader/FolioReader-Android.svg?branch=master)](https://travis-ci.org/FolioReader/FolioReader-Android)

FolioReader-Android is an ePub reader and parser framework written in Java.

### How to use

To create a book, you need to:
- [x] change package name , in sample.gradle file :

  defaultConfig {
    applicationId "com.some.name.sample"
	
- [x] apply the book in epub format to assets. Load it from Home activity
folioReader.openBook(R.raw.aayesha);

- [x] in strings_key.xml provide admob app id and admob banner id.

## WIKI

* [Custom Configuration](https://github.com/FolioReader/FolioReader-Android/wiki/Custom-Configuration)
* [Highlight Event](https://github.com/FolioReader/FolioReader-Android/wiki/Highlight-Event)
* [Providing External Highlight](https://github.com/FolioReader/FolioReader-Android/wiki/Providing-External-Highlight)

## License
FolioReaderKit is available under the BSD license. See the [LICENSE](https://github.com/FolioReader/FolioReader-Android/blob/master/License.md) file.
