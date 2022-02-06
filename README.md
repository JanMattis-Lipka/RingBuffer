# RingBuffer 
This Java project features a RingBuffer implementation.
A RingBuffer holds a specified amount of objects and overwrites the oldest
object if the maximum size has already been hit. 
It features:
  1. append, getFirst/Last/(index), dropFirst/Last, set(index) in O(1).
  2. removal of a given entry in O(n).
  3. reset, resize in O(n).
  4. contains in O(n). 

Furthermore it comes with a little terminal simulation to test certain
functionality for a String RingBuffer.
Currently it does not support null elements.
  
# Disclaimer: 
This project has been originally uploaded to get familiar with git and github. You can technically still use it, but it might not be updated anymore, nor fulfil any high standards. Same goes for licensing, the implementation relies on ArrayLists, and those fall under Oracle's licensing.
