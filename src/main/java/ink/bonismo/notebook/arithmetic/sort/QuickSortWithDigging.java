package ink.bonismo.notebook.arithmetic.sort;

import java.util.Arrays;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/12 10:53 PM
 * @Description: 快速排序挖坑法
 * @Version: 1.0
 * @link https://blog.csdn.net/libaineu2004/article/details/82253412
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195042&idx=1&sn=2b0915cd2298be9f2163cc90a3d464da&chksm=8c99f9f8bbee70eef627d0f5e5b80a604221abb3a1b5617b397fa178582dcb063c9fb6f904b3&scene=21#wechat_redirect
 */
public class QuickSortWithDigging {

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件： startIndex >= endIndex
        if (startIndex > endIndex) {
            return;
        }

        // 获取基准元素索引
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 分治法递归数列两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        // 获取第一个元素作为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        // digging position，初始 = pivot 位置
        int index = startIndex;

        // 大循环在左右指针重合或者交错时结束
        while (right >= left) {
            // right 指针从右向左进行比较
            while (right >= left) {
                // 如果右边数值（数组较大索引）小于基准元素，位置互换。左索引向右（较大方向索引）移动，左索引递增
                if (arr[right] < pivot) {
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                // 如果右边数值大于基准元素，留在原位，右索引递减（向较小方向索引）。
                right--;
            }
            // left 指针从左向右比较
            while (right >= left) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                // 如果左边数值小于基准元素，留在原位，做索引递增（向较大方向）
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
