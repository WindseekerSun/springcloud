package com.windseeker.servicezool;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ServiceZuulApplication.class)
//@SpringBootConfiguration
public class SearchMethod {
    public static int[][] largeArray = new int[1000][1000];
    private static int searchTimes = 0;
    // @Autowired
    // private ZuulFilter myFilter;

    // @Before
    public static void readArrayFromTxt() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\intArray.txt"));
        String line;
        int index = 0;
        while ((line = bufferedReader.readLine()) != null) {
            readLineToArray(index++, largeArray, line);
        }
        bufferedReader.close();
    }

    // @Test
    public static void findIndexB(int targetNo) {
        long begin = System.currentTimeMillis();
        int[] indexes = getIndexByRecurrence(targetNo, largeArray, 0, largeArray.length * largeArray[0].length);
        long end = System.currentTimeMillis();
        long second = end - begin;
        if (indexes == null)
            System.out.println("B==TargetNo not found,100000 times :" + second + "ms");
        else
            System.out.println("B==TargetNo foud,index(x,y):(" + indexes[0] + "," + indexes[1] + "),100000 times :" + second + "ms");
    }

    /**
     * 通过递归的方式查找索引
     *
     * @param targetNo
     * @param largeArray
     * @param begin
     * @param end
     * @return
     */
    private static int[] getIndexByRecurrence(int targetNo, int[][] largeArray, int begin, int end) {
        int middle = (begin + end) / 2;
        int[] middleValueAndPosition = getMiddleValue(largeArray, middle);
        if (begin == middle)
            return null;
        System.out.println("查询次数：" + (++searchTimes) + "中间值：" + middle + "数组值：" + middleValueAndPosition[2]);
        if (middleValueAndPosition[2] == targetNo) {
            return middleValueAndPosition;
        }
        if (middleValueAndPosition[2] > targetNo) {
            return getIndexByRecurrence(targetNo, largeArray, begin, middle);
        } else {
            return getIndexByRecurrence(targetNo, largeArray, middle, end);
        }
    }

    /**
     * 获取数组中指定位置的值
     *
     * @param largeArray
     * @param middle
     * @return
     */
    private static int[] getMiddleValue(int[][] largeArray, int middle) {
        int row = middle / largeArray.length;
        int column = middle % largeArray[0].length;
        return new int[]{row, column, largeArray[row][column]};
    }

    private static void readLineToArray(int index, int[][] largeArray, String line) {
        String[] nums = line.split(" ");
        for (int i = 0; i < nums.length; i++) {
            largeArray[index][i] = Integer.parseInt(nums[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        readArrayFromTxt();
        findIndexB(-1);
    }

    /**
     * 仅运行一次生成二维数组
     *
     * @throws Exception
     */
    // @Test
    public void generateIntegerArray() throws Exception {
        int begin = 0;
        FileWriter fileWriter = new FileWriter("D:\\intArray.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random random = new Random();
        for (int j = 0; j < 1000; j++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                begin += random.nextInt(100);
                stringBuilder.append(begin + " ");
            }
            bufferedWriter.write(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString() + "\n");
            bufferedWriter.flush();
        }
        fileWriter.close();
        bufferedWriter.close();
    }

    // @Test
    public void testReadResult() {
        Assert.assertTrue(largeArray[999][0] == 49440106);
    }

    // @Test
    public void findIndexA() {
        int targetNo = 38468295;
        long begin = System.currentTimeMillis();
        int indexI = -1;
        int indexJ = -1;
        int testTimes = 0;
        while (testTimes++ < 100000)
            for (int i = 0; i < largeArray.length; i++) {
                for (int j = 0; j < largeArray[i].length; j++) {
                    if (largeArray[i][j] == targetNo) {
                        indexI = i;
                        indexJ = j;
                        break;
                    }
                }
            }
        long end = System.currentTimeMillis();
        long second = end - begin;
        if (indexI == -1)
            System.out.println("A==TargetNo not found,100000 times :" + second + "ms");
        else
            System.out.println("A==TargetNo foud,index(x,y):(" + indexI + "," + indexJ + "),100000 times :" + second + "ms");
    }
}
