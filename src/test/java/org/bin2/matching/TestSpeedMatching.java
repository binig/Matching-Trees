package org.bin2.matching;

import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import org.bin2.matching.context.ContextEntry;
import org.bin2.matching.context.MatchingContext;
import org.bin2.matching.context.SearchCriteria;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by benoitroger on 28/03/14.
 */
public class TestSpeedMatching {

    @Test(enabled = false)
    public void test() {
        Stopwatch init = Stopwatch.createStarted();
        MatchingContext.Builder<TestProfile> builder =  MatchingContext.<TestProfile>newBuilder()
                .withContextEntryFunction(new Function<TestProfile, ContextEntry>() {
                    @Override
                    public ContextEntry apply(TestProfile testProfile) {
                        return ContextEntry.newBuilder().withId(testProfile.getId())
                        .withMatch(testProfile.getMatch()).withSort(testProfile.getSort()).build();
                    }
                })
                .withSearCriteriaFunction(new Function<TestProfile, SearchCriteria>() {
                    @Override
                    public SearchCriteria apply(TestProfile testProfile) {
                        return new SearchCriteria(testProfile.getMatch(), testProfile.getSort());
                    }
                });
        for (int i= 0;i<50000000;i++) {
            builder.addProfile(TestProfile.random(i));
        }
        MatchingContext<TestProfile> ctxt = builder.build();
        init.stop();
        System.out.println(init);
        for (int i=0;i<10;i++) {
            Stopwatch watch = Stopwatch.createStarted();
            System.out.println(ctxt.getMatch(new TestProfile(0, 2154713, 122)).length);
            watch.stop();
            System.out.println(watch);
        }
        Assert.assertTrue(false);
    }
}
