File tree traversal

```java
Files.walk(Paths.get("")).forEach(System.out::println);
```

Files.java
```java
public static Stream<Path> walk(Path start,
                                    int maxDepth,
                                    FileVisitOption... options) throws IOException {
    FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options);
    try {
        return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.DISTINCT), false)
                            .onClose(iterator::close)
                            .map(entry -> entry.file());
    } catch (Error|RuntimeException e) {
        iterator.close();
        throw e;
    }
}
    
```

FileTreeIterator.java

```java
class FileTreeIterator implements Iterator<Event>, Closeable {
    private final FileTreeWalker walker;
    private Event next;

    ...
    
    private void fetchNextIfNeeded() {
        if (next == null) {
            FileTreeWalker.Event ev = walker.next();
            while (ev != null) {
                IOException ioe = ev.ioeException();
                if (ioe != null)
                    throw new UncheckedIOException(ioe);

                // END_DIRECTORY events are ignored
                if (ev.type() != FileTreeWalker.EventType.END_DIRECTORY) {
                    next = ev;
                    return;
                }
                ev = walker.next();
            }
        }
    }
    
    ...

    @Override
    public Event next() {
        if (!walker.isOpen())
            throw new IllegalStateException();
        fetchNextIfNeeded();
        if (next == null)
            throw new NoSuchElementException();
        Event result = next;
        next = null;
        return result;
    }

    @Override
    public void close() {
        walker.close();
    }
}
```

FileTreeWalker.java

```java
class FileTreeWalker implements Closeable {

  ...
  
  private Event visit(Path entry, boolean ignoreSecurityException, boolean canUseCached) {
        
        ...

        stack.push(new DirectoryNode(entry, attrs.fileKey(), stream));
        return new Event(EventType.START_DIRECTORY, entry, attrs);
    }
    ...
  private Event next() {
        DirectoryNode top = stack.peek();
        if (top == null)
            return null;      // stack is empty, we are done

        // continue iteration of the directory at the top of the stack
        Event ev;
        do {
            ...

            // get next entry in the directory
            ...

            // no next entry so close and pop directory, creating corresponding event
            ...
            
            // visit the entry
            ev = visit(entry,
                       true,   // ignoreSecurityException
                       true);  // canUseCached

        } while (ev == null);

        return ev;
    }
```
