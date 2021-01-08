/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: A program that has the methods for Doubly Linked List with linkedListIterator methods
 * Due: 10/25/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: _Derya O. Kurin
 */


package o_Kurin_D_Project_7;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import o_Kurin_D_Project_7.Exercise_24_03.TwoWayLinkedList;
import o_Kurin_D_Project_7.Exercise_24_03.TwoWayLinkedList.Node;


public class Exercise_24_03 {	
	class TwoWayLinkedList<E> implements MyList<E> {
		
		private Node<E> head, tail;
		private int size; // Number of elements in the list
		
		/** Create an empty list */
		public TwoWayLinkedList() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		/** Create a list from an array of objects */
		public TwoWayLinkedList(E[] objects) {
			for (int i = 0; i < objects.length; i++) {
		         add(i, objects[i]);
			}
		}
		
		/** Return the head element in the list */
		public E getFirst() {
			if(head == null) {
				return null;
			} else {
				return head.data;
			}
		}
		
	    /** Return the last element in the list */
		public E getLast() {
			if(size == 0) {
				return null;
			} else {
				return tail.data;
			}
		}
		
		
		/**	Add an element to the beginning of the list  */
		public void addFirst(E e) {
			Node<E> newNode = new Node<>(e);
			newNode.next = head;
			head = newNode;
			size++;
			
			//If the new node is the only node in the list
			if(tail == null) {
				tail = head;
			} else {
				Node<E> node = new Node<>(e, head, null);
				head.prev = node;
				node.next = head;
				head = node;
				size++;
			}
		}
		
		/**	Add an element to the end of the list  */
		public void addLast(E e) {
			//Create a new node
			Node<E> newNode = new Node<>(e, null, tail);
			
			if (size == 0) {
                head = tail = new Node(e);
                
            // The new node is the only node in list
            } else {
                tail = newNode;
                if (size == 1) {
                    head.next = tail;
                    tail.prev = head;
                    
                }
                if (size == 2) {
                   head.next = new Node<>(e, tail, head); 
                }
            }
            size++; 

		} 
		
		/** Add a new element at the specified index */
		@Override
		public void add(int index, E e) {
			if (index < 0 || index > size) {
				//an exception could be thrown here but omitted,
				//instead the method will do nothing
			}	
			
			Node<E> newNode = new Node<E>(e);
			// If the list is empty
			if (head == null) {		
				head = newNode;
				tail = newNode;
			}
			
			// If the index is the head 
			else if (index == 0) {			
				newNode.next = head;
				head.prev = newNode;
				head = newNode;
			}
			// If we insert it to the tail
			else if (index == size) { 	
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
			}
			// If the index is in between the head and the tail
			else {					
				Node<E> node = head;
				for (int i = 1; i < index; i++) 
					node = node.next;
					newNode.next = node.next;
					node.next = newNode;
					newNode.prev = node;
					newNode.next.prev = newNode;
			}
			size++;
		}
		
	
		/** Remove the first node of the list */
		public E removeFirst() {
			// Nothing to delete
			if (head == null) {
				return null; 
			} 
			else {		
			 	// Keep the first node in a temporary variable
			    Node<E> temp = head; 
			    head = head.next; 
			    size--; 
			    if (head == null) {
			    	tail = null; 
			    }  else {
			    	head.prev = null;
			    }
			    // Return the deleted element
			    return temp.data; 
			}
			
		}
		
		/** Remove the last node of the list */
		public E removeLast() {
			if(size == 0) {
				return null;
			} else if(size == 1) {
				Node<E> temp = head;
				head = tail = null;
				size = 0;
				return temp.data;
				
			} else {
				
				Node<E> temp = tail;
				tail = tail.prev;
				tail.next = null;
				   size--;
				   return temp.data;
			}
		}
		
		/** Remove the element at the specified index */
		@Override
		public E remove(int index) {
			if (index < 0 || index >= size) return null; 
			else if(index == 0) return removeFirst(); //Remove first
			else if (index == size -1) return removeLast(); // Remove last
			else {
				Node<E> previous = head;
				for (int i = 1; i < index; i++) {
					previous = previous.next;
				}
				
				Node<E> current = previous.next;
				previous.next = current.next;
				previous.next.prev = previous;
			    size--;
				return current.data;
			}
		}
		
		
		/** Override toString to return elements in the list */
		@Override 
		public String toString() {
			StringBuilder result = new StringBuilder("[");
			
			Node<E> current = head;
			for (int i = 0; i < size; i++) {
				result.append(current.data);
				current = current.next;	
				if(current != null) {
					result.append(", ");				
				} else {
					result.append("]");
				}
			}
			return result.toString();
		 }

		
		
		/** Clear the list */
		@Override
		public void clear() {
			size = 0;
			head = tail = null;
		}
		
		@Override /** Return true if this list contains the element e */
		public boolean contains(Object e) {
			if(size == 0) {
				return false;
			} else {
				Node<E> temp = head;
				while(temp != null) {
				    if(temp.data.equals(e)) {
				    	return true;
				    } else {
				    	temp = temp.next;
				    }
				}
			}		
			return false;
		}
		
		
	    @Override /** Return the element at the specified index */
	    public E get(int index) {
	    	checkIndex(index);
	    	Node<E> result = head;
	    	for (int i = 0; i < index; i++) {
	    	   result = result.next;
	    	}
	        return result.data;
	    }
	    
	    @Override /** Return the index of the head matching argument in
	    * this list. Return −1 if no match. */
	    public int indexOf(Object e) {
	    	// Left as an exercise
			return 0;
	    }
	    
	    
	   
	    /** Return the index of the last matching element in 
	    * this list. Return −1 if no match. */
	    @Override
	    public int lastIndexOf(E e) {
	        // Left as an exercise
	    	return 0;
	    }
	    
	    
	    
	    @Override /** Return the index of the last matching element in
	    * in this list with the specified element. */
	    public E set(int index, E e) {
	    	// Left as an exercise
	    	return null;
	    }
	    
	    /** Check if the index is throwing any exception */
	    public void checkIndex(int index) {
  		  if (index < 0 || index >= size)
  		   throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
  		     + size);
	    }
	   
	    /** Override iterator() defined in Iterable */
	    public java.util.Iterator<E> listIterator() {
	    	return new LinkedListIterator();
	    }
	    
	    public java.util.Iterator<E> listIterator(int index) {
            return new LinkedListIterator(index);
        }
	    
	

	    
	    
	    class LinkedListIterator implements java.util.Iterator<E> {	
	    	private Node<E> current;

            public LinkedListIterator() {
                current = head;
            }

            public LinkedListIterator(int index) {
                current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            public boolean hasPrevious() {
                return current != null;
            }

            public E previous() {
                current = current.prev;
                return current.data;
            }

            @Override
            public E next() {
                current = current.next;
                return current.data;
            }
	}
	
	
	class Node<E> {
		private E data;
        private Node prev;
        private Node next;
        
        public Node(E element) {
            this(element, null, null);
        }
        
        public Node(E element, Node e) {
            this(element, e, null);
        }
        
        public Node(E element, Node e1, Node e2) {
            data = element;
            prev = e2;
            next = e1;
        }
        
        public void setPrevious(Node e) {
        	prev = e;
        	e.prev = this;
        }
        
        public Node getNext() {
            return this.next;
        }

        public Node getPrevious() {
            return this.prev;
        }

        public void setData(E element) {
            this.data = element;
        }

        public E getData() {
            return this.data;
        }
       
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}       
	}	
}

