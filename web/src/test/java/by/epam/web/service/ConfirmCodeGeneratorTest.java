package by.epam.web.service;

import by.epam.web.constant.PatternConst;
import org.testng.Assert;

public class ConfirmCodeGeneratorTest {

    @org.testng.annotations.Test
    public void testGenerateString1() {
        String code = ConfirmCodeGenerator.generateString();
       Assert.assertTrue((code.length() == 4) && (code.matches(PatternConst.CODE_PATTERN)));
    }
}