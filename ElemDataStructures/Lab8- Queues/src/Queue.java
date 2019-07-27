/**
 *
 * The point of this lab is to demonstrate the use of queues within arrays.
 * Change the enqueue method so that when the array is full, it doubles in size.
 * Note: I attempted to used System.arraycopy but obtained Null Pointer Exception... To fix the issue, I was forced to
 *  do it manually.
 *
 * Used with Runner.java
 */
public class Queue {
    private int QUEUE_SIZE = 5; //highest capacity of queue
    private Object[] items; //object which holds the items from queue
    private int front, back, count; //trackers


    public Queue() {
        items = new Object[QUEUE_SIZE]; //instantiate
        front = 0;
        back = QUEUE_SIZE -1;
        count =0;
    }

    /* Check if array is empty */
    public boolean isEmpty(){
        return count == 0;
    }

    /* Check if array is full */
    public boolean isFull(){
        return count == QUEUE_SIZE;
    }

    /* Add given item to the back of the list */
    public void enqueue(Object newItem){
        if (!isFull()){ //if array is not at capacity
            back = (back+1) % QUEUE_SIZE; //next available position
            items[back] = newItem; //insert new item in the indicated position
            count++; //increment tracker for the number of elements in list

        }


        /* If queue is at capacity */
        else{

            back = QUEUE_SIZE - 1;
            QUEUE_SIZE = (QUEUE_SIZE * 2); //double capacity of array

            /* Print what the queue's size has been doubled too */
            System.out.print("Queue is full. Doubling the size to " + QUEUE_SIZE + ".");

            Object[] temp = new Object[QUEUE_SIZE]; //temporary variable to hold the original queue that has now doubled in size
            int pos = 0; //keeps track of the position within temporary variable

            /* Copy the original queue to a temporary one */
            for (int i = front; i < items.length; i++){ //As long as i is the same as front, and is less than the size, traverse front
                temp[pos] = items[i]; //copy the item in position i, to the item in position j
                pos++; //traverse to the next position
            }


            /* Note: Without this a Null Pointer Exception will occur. This ensures that all the elements within the original queue
                has been copied, plus the newly added element, as well as extra space that was the result of the doubling*/


            for (int i = 0; i < front; i++){ //As long as i is less than the position of the top element
                temp[pos] = items[i]; //copy the value of position i in items to j
                pos++; //traverse to the next position
            }

            items = temp; //The temp variable that holds the expanded queue is now copied into the original
            front = 0; //front is still 0 because its the first element
            back = (back + 1) % QUEUE_SIZE; //the original queue's back is now incremented so that the new element can be added
            items[back] = newItem; //place item in that position
            count++; //increment tracker for number of elements within the list

        }

    }

    /* Retrieves the value of the front item and then removes it from Queue */
    public Object dequeue(){

        if (!isEmpty()){ //if list is empty
            Object temp = items[front]; //hold front most item in place
            front = (front+1) % QUEUE_SIZE; //move front pointer
            count--; //number of elements within the queue decreases
            return temp; //return new front
        }else
            System.out.println("Trying to dequeue from empty queue");
        return null;
    }

    /* This method clears the list */
    public void dequeueAll(){

        items = new Object[QUEUE_SIZE]; //instantiate object
        front = 0; //first of the list is nothing
        back = QUEUE_SIZE -1; //last item of the list is nothing
        count =0; //counter is nothing
        /* There is nothing in this list*/
    }

    /* This method looks at the first element but does not delete it*/
    public Object peek(){

        if (!isEmpty()) { //If array is not empty
            return items[front]; //return the value of front element
        }else
            System.out.println("Trying to peek with empty queue");
        return null;
    }

    /* This method keeps up with the number of elements within the list*/
    public int size(){
        return count; //return number of elements
    }

}
