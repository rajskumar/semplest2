﻿function disableIsLaunched() {
//    $('.k-textbox').attr('disabled', 'disabled');
//    $('.k-dropdownlist').attr('disabled', 'disabled');
//    $('input').attr('disabled', true);
}
function disableIsLaunchedCompleted() {
//    $('.k-textbox').attr('disabled', 'disabled');
//    $('.k-dropdownlist').attr('disabled', 'disabled');
//    $('.k-button').attr('disabled', 'disabled');
//    $('input').attr('disabled', true);
}
function enableIsLaunched() {
//    $('.k-textbox').attr('disabled', 'disabled');
//    $('.k-dropdownlist').attr('disabled', 'disabled');
//    $('input').attr('disabled', true);
}
function openAllTabs(displayCategories) {
    tabStrip.enable(tab.next(), tab.next().hasClass("k-state-disabled"));
    if (($('input[id="HasSiteLinks"]').val() == 'True')) {
        tabStrip.append({
            text: "Additional Links",
            contentUrl: $('#AdditionalSiteLinksUrl').val()
        }, tabStrip.tabGroup.children("li:last")).select();
    }
    if (($('input[id="HasNegativeKeywords"]').val() == 'True')) {
        tabStrip.append({
            text: "Negative Keywords",
            contentUrl: $('#NegativeKeyWordsUrl').val()
        }, tabStrip.tabGroup.children("li:last")).select();
    }
    if (displayCategories) {
        tabStrip.append({
            text: "Categories",
            contentUrl: $('#CategoriesUrl').val()
        }, tabStrip.tabGroup.children("li:last")).select();
    }
    tabStrip.append({
        text: "Billing & Launch",
        contentUrl: $('#BillingLaunchUrl').val()
    }, tabStrip.tabGroup.children("li:last")).select();
    if (($('input[id="HasKeywords"]').val() == 'True')) {
        tabStrip.append({
            text: "View Keywords",
            contentUrl: $('#ViewSelectedKeywordsUrl').val()
        }, tabStrip.tabGroup.children("li:last")).select();
    }
}
function enableDisable() {
    if ($('#IsCompleted').val() == 'True') {
        $(":input").attr("readonly", true);
        $(":button").attr("readonly", true);
    }

//    if ($('#IsLaunched').val() == 'True') {
//        //alert($('#IsCompleted').val());
//        if ($('#IsCompleted').val() == 'True') {
//            disableIsLaunchedCompleted();
//            openAllTabs();
//        } else {
//            disableIsLaunched();
//            openAllTabs();
//        }
//    }
}
function enabledIsLaunched() {
//    $('.k-textbox').removeAttr('disabled');
//    $('.k-dropdownlist').removeAttr('disabled');
//    $('input').removeAttr('disabled');
}