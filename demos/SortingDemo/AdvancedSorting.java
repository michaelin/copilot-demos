import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class AdvancedSorting {
    
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private static final int PARALLEL_THRESHOLD = 1000;
    
    public static void optimizedMergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }
    
    private static void mergeSort(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return;
        
        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }
        
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        
        if (arr[mid] <= arr[mid + 1]) return;
        
        merge(arr, temp, left, mid, right);
    }
    
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        System.arraycopy(arr, left, temp, left, right - left + 1);
        
        int i = left, j = mid + 1, k = left;
        
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        
        while (i <= mid) arr[k++] = temp[i++];
        while (j <= right) arr[k++] = temp[j++];
    }
    
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    public static void parallelMergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new ParallelMergeSortTask(arr, 0, arr.length - 1));
        pool.shutdown();
    }
    
    private static class ParallelMergeSortTask extends RecursiveAction {
        private final int[] arr;
        private final int left, right;
        
        public ParallelMergeSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }
        
        @Override
        protected void compute() {
            if (left >= right) return;
            
            if (right - left + 1 < PARALLEL_THRESHOLD) {
                int[] temp = new int[arr.length];
                mergeSort(arr, temp, left, right);
                return;
            }
            
            int mid = left + (right - left) / 2;
            
            ParallelMergeSortTask leftTask = new ParallelMergeSortTask(arr, left, mid);
            ParallelMergeSortTask rightTask = new ParallelMergeSortTask(arr, mid + 1, right);
            
            invokeAll(leftTask, rightTask);
            
            if (arr[mid] <= arr[mid + 1]) return;
            
            int[] temp = new int[right - left + 1];
            merge(arr, temp, left, mid, right);
        }
        
        private void merge(int[] arr, int[] temp, int left, int mid, int right) {
            System.arraycopy(arr, left, temp, 0, right - left + 1);
            
            int i = 0, j = mid - left + 1, k = left;
            int leftEnd = mid - left, rightEnd = right - left;
            
            while (i <= leftEnd && j <= rightEnd) {
                if (temp[i] <= temp[j]) {
                    arr[k++] = temp[i++];
                } else {
                    arr[k++] = temp[j++];
                }
            }
            
            while (i <= leftEnd) arr[k++] = temp[i++];
            while (j <= rightEnd) arr[k++] = temp[j++];
        }
    }
    
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int n = arr.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    public static void quickSort3Way(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        shuffle(arr);
        quickSort3Way(arr, 0, arr.length - 1);
    }
    
    private static void quickSort3Way(int[] arr, int low, int high) {
        if (low >= high) return;
        
        int lt = low, gt = high;
        int pivot = arr[low];
        int i = low + 1;
        
        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, lt++, i++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        
        quickSort3Way(arr, low, lt - 1);
        quickSort3Way(arr, gt + 1, high);
    }
    
    private static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            swap(arr, i, j);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * Utility method to check if array is sorted
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
    
    /**
     * Utility method to print array
     */
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}