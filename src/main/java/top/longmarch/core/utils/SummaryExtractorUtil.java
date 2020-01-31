package top.longmarch.core.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.summary.TextRankSentence;
import org.lionsoul.jcseg.extractor.SummaryExtractor;
import org.lionsoul.jcseg.extractor.impl.TextRankKeyphraseExtractor;
import org.lionsoul.jcseg.extractor.impl.TextRankSummaryExtractor;
import org.lionsoul.jcseg.sentence.SentenceSeg;
import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class SummaryExtractorUtil {

    /**
     * 获取文章摘要
     * https://gitee.com/lionsoul/jcseg
     * @param content
     * @return
     */
    public static String getSummary(String content) {
        try {
            JcsegTaskConfig config = new JcsegTaskConfig(true);
            config.setClearStopwords(true);     //设置过滤停止词
            config.setAppendCJKSyn(false);      //设置关闭同义词追加
            config.setKeepUnregWords(false);    //设置去除不识别的词条
            ADictionary dic = DictionaryFactory.createSingletonDictionary(config);
            ISegment seg = SegmentFactory.createJcseg(
                    JcsegTaskConfig.COMPLEX_MODE,
                    new Object[]{config, dic}
            );
            SummaryExtractor extractor = new TextRankSummaryExtractor(seg, new SentenceSeg());
            return extractor.getSummary(new StringReader(content), 128);
        } catch (Exception e) {

        }
        return null;
    }

    public static List<String> getDuanYu(String document) {
        //1, 创建Jcseg ISegment分词对象
        JcsegTaskConfig config = new JcsegTaskConfig(true);
        config.setClearStopwords(false);    //设置不过滤停止词
        config.setAppendCJKSyn(false);      //设置关闭同义词追加
        config.setKeepUnregWords(false);    //设置去除不识别的词条
        config.setEnSecondSeg(false);       //关闭英文自动二次切分
        ADictionary dic = DictionaryFactory.createSingletonDictionary(config);
        ISegment seg = null;
        try {
            seg = SegmentFactory.createJcseg(
                    JcsegTaskConfig.COMPLEX_MODE,
                    new Object[]{config, dic}
            );
        } catch (JcsegException e) {
            e.printStackTrace();
        }

        //2, 构建TextRankKeyphraseExtractor关键短语提取器
        TextRankKeyphraseExtractor extractor = new TextRankKeyphraseExtractor(seg);
        extractor.setMaxIterateNum(100);        //设置pagerank算法最大迭代词库，非必须，使用默认即可
        extractor.setWindowSize(5);             //设置textRank窗口大小，非必须，使用默认即可
        extractor.setKeywordsNum(15);           //设置最大返回的关键词个数，默认为10
        extractor.setMaxWordsNum(4);            //设置最大短语词长，默认为5

        //3, 从一个输入reader输入流中获取短语
        String str = "支持向量机广泛应用于文本挖掘，例如，基于支持向量机的文本自动分类技术研究一文中很详细的介绍支持向量机的算法细节，文本自动分类是文本挖掘技术中的一种！";
        List<String> keyphrases = null;
        try {
            keyphrases = extractor.getKeyphrase(new StringReader(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(keyphrases);
        return keyphrases;
    }


    public static void main(String[] args) throws Exception {
        String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n" +
                "算法可以宽泛的分为三类，\n" +
                "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n" +
                "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n" +
                "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
        List<String> sentenceList = HanLP.extractSummary(document, 3);
        System.out.println(sentenceList);
        System.out.println(getSummary(document));
        String content = "程序员(英文Programmer)是从事程序开发、维护的专业人员。一般将程序员分为程序设计人员和程序编码人员，但两者的界限并不非常清楚，特别是在中国。软件从业人员分为初级程序员、高级程序员、系统分析员和项目经理四大类。";

        List<String> topSentenceList = TextRankSentence.getTopSentenceList(document, 3);
        System.out.println(topSentenceList);

        List<String> keywordList = HanLP.extractKeyword(document, 5);
        System.out.println(keywordList);


        //1, 创建Jcseg ISegment分词对象
        JcsegTaskConfig config = new JcsegTaskConfig(true);
        config.setClearStopwords(false);    //设置不过滤停止词
        config.setAppendCJKSyn(false);      //设置关闭同义词追加
        config.setKeepUnregWords(false);    //设置去除不识别的词条
        config.setEnSecondSeg(false);       //关闭英文自动二次切分
        ADictionary dic = DictionaryFactory.createSingletonDictionary(config);
        ISegment seg = SegmentFactory.createJcseg(
                JcsegTaskConfig.COMPLEX_MODE,
                new Object[]{config, dic}
        );

        //2, 构建TextRankKeyphraseExtractor关键短语提取器
        TextRankKeyphraseExtractor extractor = new TextRankKeyphraseExtractor(seg);
        extractor.setMaxIterateNum(100);        //设置pagerank算法最大迭代词库，非必须，使用默认即可
        extractor.setWindowSize(5);             //设置textRank窗口大小，非必须，使用默认即可
        extractor.setKeywordsNum(15);           //设置最大返回的关键词个数，默认为10
        extractor.setMaxWordsNum(4);            //设置最大短语词长，默认为5

        //3, 从一个输入reader输入流中获取短语
        String str = "支持向量机广泛应用于文本挖掘，例如，基于支持向量机的文本自动分类技术研究一文中很详细的介绍支持向量机的算法细节，文本自动分类是文本挖掘技术中的一种！";
        List<String> keyphrases = extractor.getKeyphrase(new StringReader(str));
        System.out.println(keyphrases);

    }


}
