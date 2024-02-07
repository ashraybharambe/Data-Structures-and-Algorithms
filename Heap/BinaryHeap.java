public class BinaryHeap {

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(50);
        minHeap.insertKey(1);
        minHeap.insertKey(2);
        minHeap.insertKey(3);
        minHeap.insertKey(4);
        minHeap.insertKey(5);
        minHeap.printHeapArray();

        System.out.println(minHeap.extractMin());
        minHeap.printHeapArray();
        System.out.println(minHeap.getMin());
        minHeap.decreaseKey(2, 1);
        minHeap.printHeapArray();
    }

    public static class MinHeap {
        private int[] heapArray;
        private int capacity;
        private int currHeapSize;

        public MinHeap(int capacity) {
            this.capacity = capacity;
            heapArray = new int[capacity];
            this.currHeapSize = 0;
        }

        public int getMin() {
            if (heapArray.length != 0) {
                return heapArray[0];
            }
            return Integer.MAX_VALUE;
        }

        public boolean insertKey(int key) {
            if (currHeapSize == capacity) {
                return false;
            }
            heapArray[currHeapSize] = key;
            traverseUp(currHeapSize);
            currHeapSize++;
            return true;
        }

        public void deleteKey(int key) {
            heapArray[key] = Integer.MAX_VALUE;
            minHeapify(key);
            currHeapSize--;
        }

        public void printHeapArray () {
            for(int i = 0; i < currHeapSize; i++) {
                System.out.print(heapArray[i] + " ");
            }
            System.out.println();
        }

        //Assumption: newValue is less than existing value
        public void decreaseKey(int key, int newValue) {
            heapArray[key] = newValue;
            traverseUp(key);
        }

        public int extractMin() {
            if (currHeapSize <= 0) {
                return Integer.MAX_VALUE;
            }
            if (currHeapSize == 1) {
                currHeapSize--;
                return heapArray[0];
            }
            int min = heapArray[0];
            heapArray[0] = heapArray[currHeapSize - 1];
            currHeapSize--;
            minHeapify(0);
            return min;
        }

        private void traverseUp(int key) {
            while (key != 0 && heapArray[key] < heapArray[parent(key)]) {
                swap(key, parent(key));
                key = parent(key);
            }
        }

        private int left(int root) {
            return (root * 2) + 1;
        }

        private int right(int root) {
            return (root * 2) + 2;
        }

        private void minHeapify(int root) {
            int leftChildIndex = left(root);
            int rightChildIndex = right(root);
            int smallest = root;
            if (leftChildIndex < currHeapSize && heapArray[leftChildIndex] < heapArray[smallest]) {
                smallest = leftChildIndex;
            }
            if (rightChildIndex < currHeapSize && heapArray[rightChildIndex] < heapArray[smallest]) {
                smallest = rightChildIndex;
            }
            if (smallest != root) {
                swap(smallest, root);
                minHeapify(smallest);
            }
        }

        private int parent(int key) {
            return (key - 1) / 2;
        }

        private void swap(int index1, int index2) {
            int temp = heapArray[index1];
            heapArray[index1] = heapArray[index2];
            heapArray[index2] = temp;
        }

    }
}
