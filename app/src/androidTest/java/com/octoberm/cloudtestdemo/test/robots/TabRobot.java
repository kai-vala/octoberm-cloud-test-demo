package com.octoberm.cloudtestdemo.test.robots;

import com.octoberm.cloudtestdemo.R;
import com.octoberm.cloudtestdemo.test.utils.Utils;
import com.octoberm.cloudtestdemo.test.utils.UtilsView;

public class TabRobot extends AbstractRobot {

    public TabRobot() {
        super(null);
    }

    public TabRobot clickClearAction() {
        UtilsView.clickResourceOnView(R.id.fab_button_clear);
        return this;
    }

    public TabRobot verifyNoText(int number) {
        UtilsView.matchTextNotOnView(R.id.section_label, getTabText(number));
        return this;
    }

    public TabRobot openTab1() {
        UtilsView.clickTextOnView(R.string.tab1_title);
        return this;
    }

    public TabRobot openTab2() {
        UtilsView.clickTextOnView(R.string.tab2_title);
        return this;
    }

    public TabRobot openTab3() {
        UtilsView.clickTextOnView(R.string.tab3_title);
        return this;
    }

    public TabRobot verifyTab1() {
        UtilsView.matchTextOnView(R.string.tab1_title);
        UtilsView.matchTextOnView(R.id.section_label, getTabText(1));
        waitTime(2000);
        return this;
    }

    public TabRobot verifyTab2() {
        UtilsView.matchTextOnView(R.string.tab1_title);
        UtilsView.matchTextOnView(R.id.section_label, getTabText(2));
        return this;
    }

    public TabRobot verifyTab3() {
        UtilsView.matchTextOnView(R.string.tab1_title);
        UtilsView.matchTextOnView(R.id.section_label, getTabText(3));
        return this;
    }

    private String getTabText(int number) {
        return String.format(Utils.getString(R.string.text_section_format), number);
    }
}
