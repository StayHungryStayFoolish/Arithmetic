package ink.bonismo.notebook.arithmetic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Created by bonismo@hotmail.com on 2019/7/14 8:22 AM
 * @Description:
 * @Version: 1.0
 * @link https://blog.csdn.net/libaineu2004/article/details/82253412
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653195042&idx=1&sn=2b0915cd2298be9f2163cc90a3d464da&chksm=8c99f9f8bbee70eef627d0f5e5b80a604221abb3a1b5617b397fa178582dcb063c9fb6f904b3&scene=21#wechat_redirect
 */
public class QuickSortWithStack {

    public static void quickSortWithStack(int[] arr, int startIndex, int endIndex) {
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        Map<String, Integer> rootParam = new HashMap<>();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        while (!quickSortStack.isEmpty()) {
            // 出栈
            Map<String, Integer> param = quickSortStack.pop();
            // 获取基准元素位置
            int pivotIndex = partition(arr, param.get("startIndex"), param.get("endIndex"));
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }

    private static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int point = arr[left];
                arr[left] = arr[right];
                arr[right] = point;
            }
        }
        int point = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = point;
        return left;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {4,7,6,5,3,2,8,1};
        quickSortWithStack(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
