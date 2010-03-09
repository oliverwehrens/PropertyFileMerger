PropertyFileMerger

This tool allows you to merge two property files (or directories of
property files).

The use case for it is that nls key files will be updated externally while working on new code and thus new keys. Now the old version of the keys comes back and you need to merge in these.

What the code will do is:

Check if for any of currently existing property files a file with potenalliy changes nls keys exists
If so, it will replace the changed nls keys in the existing properties and writes it back to a new file/directory.

