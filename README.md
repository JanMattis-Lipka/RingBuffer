# RingBuffer 
This Java project features a RingBuffer implementation.
A RingBuffer holds a specified amount of objects and overwrites the oldest
object if the maximum size has already been hit. It can be used with an ArrayList 
or a HashMap depending on what is most fitting.

It features:
 1. append in O(1)
 2. dropFirst/Last in O(1)
 3. getFirst/Last/(index) in O(1)
 4. set(index, element) in O(1)
 5. removeEntry(elem, allOccurences), removeAtIndex in O(n)
 6. default constructor, constructor with size and (optional) entries in O(n)
 7. resize(newSize) in O(n)
 8. reset/(newSize) in O(n)
 9. contains(elem) in O(n)
 10. equals(obj) in O(n)
 11. toString in O(n)
 12. hasNext, isEmpty, getUsedSize, getMaxSize in O(1)
 13. print in O(n)
 
See comments / API for more information.

Furthermore it comes with a little terminal simulation to test certain
functionality for a String RingBuffer.
Currently it does not support null elements.
  
# Disclaimer: 
This project has been originally uploaded to get familiar with git and github. You can technically still use it, but it might not be updated anymore, nor fulfil any high standards. Same goes for licensing, the implementation relies on ArrayLists, and those fall under Oracle's licensing.
