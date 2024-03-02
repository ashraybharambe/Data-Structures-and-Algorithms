import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChooseFlask {

    public static int chooseFlask(List<Integer> requirements, int flaskTypes, List<List<Integer>> markings) {
        Collections.sort(requirements);
        int[] preSum = getPreSumRequirements(requirements);
        List<List<Integer>> flasks = getFlasks(markings, flaskTypes);
        int minCost = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < flasks.size(); i++) {
            List<Integer> flaskMarks = flasks.get(i);
            if(flaskMarks.isEmpty() || requirements.get(requirements.size() - 1) > flaskMarks.get(flaskMarks.size() - 1)) {
                continue;
            }
            int lowerBound = 0;
            int upperBound = 0;
            int cost = 0;
            for(int j = 0; j < flaskMarks.size(); j++) {
                upperBound = flaskMarks.get(j);
                int reqFirstIndex = getFirst(lowerBound, requirements);
                int reqLastIndex = getLast(upperBound, requirements);
                if(reqFirstIndex == requirements.size()) {
                    continue;
                }
                int elements = reqLastIndex - reqFirstIndex + 1;
                cost += (elements * upperBound) - (preSum[reqLastIndex] - (reqFirstIndex == 0 ? 0 : preSum[reqFirstIndex - 1]));
                lowerBound = upperBound;
            }
            if(minCost > cost) {
                minCost = cost;
                index = i;
            }
            System.out.println("index : " + i + " cost: " + cost);
        }
        return index;
    }

    public static int getLast(int value, List<Integer> requirements) {
        int low = 0;
        int high = requirements.size() - 1;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if(value < requirements.get(mid)) {
                high = mid - 1;
            } else {
                index = Math.max(index, mid);
                low = mid  + 1;
            }
        }
        return index;
    }

    public static int getFirst(int value, List<Integer> requirements ) {
        int low = 0;
        int high = requirements.size() - 1;
        int index = high + 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(value > requirements.get(mid)) {
                low = mid + 1;
            } else {
                index = Math.min(index, mid);
                high = mid - 1;
            }
        }
        return index;
    }

    public static List<List<Integer>> getFlasks (List<List<Integer>> markings, int n) {
        List<List<Integer>> flasks = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            flasks.add(new ArrayList<>());
        }
        for(List<Integer> mark : markings) {
            int flaskIndex = mark.get(0);
            int flaskMark = mark.get(1);
            flasks.get(flaskIndex).add(flaskMark);
        }
        return flasks;
    }

    public static int[] getPreSumRequirements( List<Integer> requirements) {
        int[] preSum = new int[requirements.size()];
        preSum[0] = requirements.get(0);
        for(int i = 1; i < requirements.size(); i++) {
            preSum[i] += preSum[i-1] + requirements.get(i);
        }
        return preSum;
    }

    public static void main(String[] args) {
        List<Integer> requirements = Arrays.asList(10, 15);
        int flaskTypes = 4;
        List<List<Integer>> markings = new ArrayList<>();
        markings.add(Arrays.asList(0, 11));
        markings.add(Arrays.asList(0, 20));
        markings.add(Arrays.asList(1, 11));
        markings.add(Arrays.asList(1, 17));
        markings.add(Arrays.asList(2, 12));
        markings.add(Arrays.asList(2, 16));
        markings.add(Arrays.asList(3, 3));
        int ans = chooseFlask(requirements, flaskTypes, markings);
        System.out.println(ans);
    }

}
