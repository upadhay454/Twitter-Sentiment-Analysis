package SentimentAnalys;


import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;
import com.aliasi.classify.LMClassifier;
import com.aliasi.corpus.ObjectHandler;
import com.aliasi.util.AbstractExternalizable;
import com.aliasi.util.Compilable;
import com.aliasi.util.Files;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gourav Chandra Upadhayay on 26/04/2017.
 */
public class ConfigTrain {

    private com.aliasi.util.Compilable Compilable;

    void train2() throws IOException, ClassNotFoundException {

        File trainDir;
        String[] categories;
        LMClassifier classifier;
        trainDir = new File("trainDirectory");
        categories = trainDir.list();

        int nGram = 7; //the nGram level, any value between 7 and 12 works
        classifier = DynamicLMClassifier.createNGramProcess(categories, nGram);

        for (int i = 0; i < categories.length; ++i) {

            String category = categories[i];
            Classification classification = new Classification(category);
            File file = new File(trainDir, categories[i]);
            File[] trainFiles = file.listFiles();

            for (int j = 0; j < trainFiles.length; ++j) {

                File trainFile = trainFiles[j];
                String review = Files.readFromFile(trainFile, "ISO-8859-1");
                Classified classified = new Classified(review, classification);

                ((ObjectHandler) classifier).handle(classified);
            }
        }
        AbstractExternalizable.compileTo((Compilable) classifier, new File("classifier.txt"));
    }

    void train() throws IOException, ClassNotFoundException {

        File trainDir;
        String[] categories;
        LMClassifier classifier;
        trainDir = new File("train");
        categories = trainDir.list();

        int nGram = 7; //the nGram level, any value between 7 and 12 works
        classifier = DynamicLMClassifier.createNGramProcess(categories, nGram);

        for (String f : categories) {

            Classification classification = new Classification(f);
            File file = new File(trainDir, f);

            String review = Files.readFromFile(file, "ISO-8859-1");
            Classified classified = new Classified(review, classification);

            ((ObjectHandler) classifier).handle(classified);

        }
        AbstractExternalizable.compileTo((Compilable) classifier, new File("E://Netbeans//SentimentAnalysis//docs/classifier2.txt"));
    }

}
