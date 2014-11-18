FileSync
========

A Desktop App for Synchronizing Files with Appjangle Data

## Motivation



## Usage

   Session session = Nextweb.createSession();
   
   Node node = session.link("https://...").get();
   
   FileSyncJre.sync(new File("/tmp/sync"), node);

## Planned Features

- Keep a map with file hashes mapped to URIs of files they belong to. Look up this cache whenever 
a new file is inserted and use the URI of the file if a matching hash exists.

- Store value of folder nodes in children metadata.
 
  - OR store value in a special file 'value.xml' within the folder (or .value.xml)

- Add support for two files with identical names (but different extensions) in one folder.

