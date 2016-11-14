public class CircularArrayList extends AbstractArrayList implements CircularCollectible {

    public CircularArrayList() {
        super();
    }

    public CircularArrayList(String[] elements) {
        super(elements);
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void add(String s) {
        if (size < arraySize) {
            elements[size] = s;
            size ++;
        } else {
            arraySize = arraySize * 2;
            String[] newArray = new String[arraySize];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
            this.add(s);
        }
    }
    
    public String first() {
        int i = 0;
        while(elements[i] != null && i <= size) {
            i++;
        }
        return elements[i-1];
    }

    /** remove(String s):
     *  removes the first element in the list for which
     *      element.equals(s)
     *  is true.
     */
    public void remove(String s) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == s) {
                elements[i] = null;
                return;
            }
        }
    }

    /** removeAll(String s):
     *  removes all elements in the list for which
     *      element.equals(s)
     *  is true.
     */
    public void removeAll(String s) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == s) {
                elements[i] = null;
            }
        }
    }

    public CircularIterator iterator() {
        return new CircularArrayListIterator();
    }

    class CircularArrayListIterator implements CircularIterator {

        int current;

        public CircularArrayListIterator() {
            current = 0;
        }
        
        public boolean hasNext() {
            return size != 0;
        }

        public String next() {
            current++;
            if (current >= arraySize) {
                current = 0;
            }
            return elements[current];
        }

        /** remove():
         *  removes the last/previous element in the list
         *  (i.e. removes the element that was returned by the
         *  most recent call to next())
         */
        public void remove() {
            elements[current] = null;
            size --;
            next();
        }

        /** removeKth(int k):
         *  iterates through the next k elements and removes
         *  the kth one. The next call to removeKth would
         *  start at the node after the removed node.
         *  (i.e. kthNode.next)
         */
        public String removeKthElement(int k) {
            for (int i = 1; i < k; i++) {
                next();
            }
            String removeElement = elements[current];
            remove();
            return removeElement;
        }

        public boolean oneElementLeft() {
            return size == 1;
        }
    }

}
