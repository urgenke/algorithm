package didi_algorithm;

import java.util.*;

/**
 * Date: 2018/9/18 下午7:48
 * Author: qianhangkang
 * Description:
 * 题目描述：
 * 英文单词拼写的时候可能会出现拼写错误的情况(typo)。下面的题目，我们尝试实现拼写纠错推荐的功能。
 *
 * 字串编辑距离是衡量字串间相似度的常见手段。
 *
 * ①字串编辑距离：是指利用字符操作，把字符串A转换成字符串B所需要的最少操作数。
 *
 * ②字串操作包括：删除字符(removal)、插入字符(insertion)、修改字符(substitution)。
 *
 * ③使用以下规则对推荐纠错选项进行相似度排序。得分越高，认为相似度越低
 *
 * 只涉及到26个英文字符、不区分大小写。
 *
 * 删除(removal)  3分
 *
 * 插入(insertion) 3分
 *
 * 修改(substitution) ：
 *
 *     (q w e r t a s d f g z x c v ) （y u i o p h j k l b n m）
 *
 *     以上两个分组内的字符修改1分，两个分组间字符修改 2分。
 *
 *
 * INPUT:
 * ·每行一个输入。空格分割参数。
 * ·第一个参数是目标单词(可能存在typo) ·后面若干空格分割参数(个数不定)是字典单词，作为候选纠错项(也可能和第一个参数重复)。
 *
 * OUTPUT:
 * ·按照上面的纠错规则字串相似度最小编辑距离进行排序，给出3个(如有)对应的纠错候选。
 * ·如得分相同，则按照输入顺序进行排序。
 *
 * 样例输入
 * slep slap sleep step shoe shop snap slep
 * 样例输出
 * slep slap step
 *
 *
 */
public class EnglishWorldCorrect {

    public static void main(String[] args) {

        String[] str = null;

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            str = scanner.nextLine().split(" ");
        }
        assert str != null;

        final Map<String,Integer> map = new TreeMap<>();
        final Map<String, Integer> charMap = new HashMap<>(32);

        charMap.put("q",1);
        charMap.put("w",1);
        charMap.put("e",1);
        charMap.put("r",1);
        charMap.put("t",1);
        charMap.put("a",1);
        charMap.put("s",1);
        charMap.put("d",1);
        charMap.put("f",1);
        charMap.put("g",1);
        charMap.put("z",1);
        charMap.put("x",1);
        charMap.put("c",1);
        charMap.put("v",1);
        charMap.put("y", 2);
        charMap.put("u", 2);
        charMap.put("i", 2);
        charMap.put("o", 2);
        charMap.put("p", 2);
        charMap.put("h", 2);
        charMap.put("j", 2);
        charMap.put("k", 2);
        charMap.put("l", 2);
        charMap.put("b", 2);
        charMap.put("n", 2);

        String raw = str[0].toLowerCase();
        for (int i = 1; i < str.length; i++) {
            int score = 0;
            String var = str[i].toLowerCase();
            if (raw.equals(var)) {
                map.put(var, 0);
                continue;
            }
//            for (int j = 0; j < raw.length(); j++) {
//                if (var.indexOf(raw.indexOf(i)) == -1) {
//                    //不存在该字符
//                } else {
//                    //存在
//
//                }
//            }





            map.put(var, score);
        }






    }

}
