package com.test.core.utils.extension.html_report;
@SuppressWarnings("preview")
public record HtmlTable(String fontFamily,
                        String fontSize,
                        String fontColor,
                        String border,
                        String borderCollapse,
                        String thTdPadding) {
    public HtmlTable() {
        this("Consalas", "16px", "fontColor",
                "1px solid #383737", "collapse", "3px 10px 3px 10px");
    }
}