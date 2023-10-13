package com.digital.ui.footer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.HashMap;
import java.util.Properties;

public class FooterLinks {

    @FindBys(@FindBy(xpath = "//a[@href='/contact-us.html']"))
    private static WebElement ContactUs;

    @FindBys(@FindBy(xpath = "//a[@href='/faq.html']"))
    private static WebElement FAQ;

    @FindBys(@FindBy(xpath = "//a[@href='/about-us/our-brands.html']"))
    private static WebElement OurBrands;

    @FindBys(@FindBy(xpath = "//a[@href='/about-us/mobile-apps.html']"))
    private static WebElement MobileApps;

    @FindBys(@FindBy(xpath = "//a[@href='https://albertsons.az1.qualtrics.com/jfe/form/SV_6WEsX4eEQbIGu4R?Banner=safeway']"))
    private static WebElement TakeOnlineSurvey;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.safeway.com/yourstore/sweepstakes-rules.html']"))
    private static WebElement SweepstakesRules;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.safeway.com/yourstore/fast-forward.html']"))
    private static WebElement FastForward;

    @FindBys(@FindBy(xpath = "//a[@href='/gifts-and-prepaid-cards.html']"))
    private static WebElement GiftandPrepaidCards;

    @FindBys(@FindBy(xpath = "//a[@href='https://local.safeway.com/']"))
    private static WebElement FindYourLocalSafeway;

    @FindBys(@FindBy(xpath = "//a[@href='/pharmacy/covid-19.html']"))
    private static WebElement COVID_19Vaccine;

    @FindBys(@FindBy(xpath = "//a[@href='/pharmacy.html']"))
    private static WebElement SafewayPharmacy;

    @FindBys(@FindBy(xpath = "//a[@href='https://www-qa2.safeway.com/shop/brand-index.html']"))
    private static WebElement AllBrandsList;

    @FindBys(@FindBy(xpath = "//a[@href='/about-us.html']"))
    private static WebElement AboutUs;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/home.html']"))
    private static WebElement AlbertsonsCompanies;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/careers/en/our-companies/safeway.html']"))
    private static WebElement Careers;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/suppliers.html']"))
    private static WebElement Suppliers;

    @FindBys(@FindBy(xpath = "//a[@href='/employee-resource-center.html']"))
    private static WebElement ForEmployees;

    @FindBys(@FindBy(xpath = "//a[@href='http://safewayfoundation.org/']"))
    private static WebElement Foundation;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/product-recalls.html']"))
    private static WebElement ProductRecalls;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies/terms-of-use.html']"))
    private static WebElement TermsofUse;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies/privacy-policy.html']"))
    private static WebElement PrivacyPolicy;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies/accessibility-statement.html']"))
    private static WebElement AccessibilityStatement;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies/hipaa-notice-of-privacy-practices.html']"))
    private static WebElement HIPAANoticeofPrivacyPractices;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies/anti-human-trafficking.html']"))
    private static WebElement AntiHumanTrafficking;

    @FindBys(@FindBy(xpath = "//a[@href='https://www.albertsonscompanies.com/about-us/our-policies.html']"))
    private static WebElement OtherPoliciesDisclosures;


    HashMap<String,String> safeway(){
        Properties p = new Properties();
        HashMap<String,FooterLinkData> footerLinkAndNavigation = new HashMap<>();

        footerLinkAndNavigation.put("Contact Us", new FooterLinkData(ContactUs, "How Can We Help You?", p.getProperty("contactUs_URL")));
        footerLinkAndNavigation.put("FAQ", new FooterLinkData(FAQ, "TextOnTargetPage", p.getProperty("FAQ_URL")));
        footerLinkAndNavigation.put("Our Brands", new FooterLinkData(OurBrands, "TextOnTargetPage", p.getProperty("OurBrands_URL")));
        footerLinkAndNavigation.put("Mobile Apps", new FooterLinkData(MobileApps, "Mobile Apps", p.getProperty("MobileApps_URL")));
        footerLinkAndNavigation.put("Take Online Survey", new FooterLinkData(TakeOnlineSurvey, "TextOnTargetPage", p.getProperty("TakeOnlineSurvey_URL")));
        footerLinkAndNavigation.put("Sweepstakes Rules", new FooterLinkData(SweepstakesRules, "TextOnTargetPage", p.getProperty("SweepstakesRules_URL")));
        footerLinkAndNavigation.put("Fast Forward", new FooterLinkData(FastForward, "TextOnTargetPage", p.getProperty("FastForward_URL")));
        footerLinkAndNavigation.put("Gift and Prepaid Cards", new FooterLinkData(GiftandPrepaidCards, "TextOnTargetPage", p.getProperty("GiftandPrepaidCards_URL")));
        footerLinkAndNavigation.put("Find Your Local Safeway", new FooterLinkData(FindYourLocalSafeway, "TextOnTargetPage", p.getProperty("FindYourLocalSafeway_URL")));
        footerLinkAndNavigation.put("COVID-19 Vaccine", new FooterLinkData(COVID_19Vaccine, "TextOnTargetPage", p.getProperty("COVID_19Vaccine_URL")));
        footerLinkAndNavigation.put("Safeway Pharmacy", new FooterLinkData(SafewayPharmacy, "TextOnTargetPage", p.getProperty("SafewayPharmacy_URL")));
        footerLinkAndNavigation.put("All Brands List", new FooterLinkData(AllBrandsList, "TextOnTargetPage", p.getProperty("AllBrandsList_URL")));
        footerLinkAndNavigation.put("About Us", new FooterLinkData(AboutUs, "TextOnTargetPage", p.getProperty("AboutUs_URL")));
        footerLinkAndNavigation.put("Albertsons Companies", new FooterLinkData(AlbertsonsCompanies, "TextOnTargetPage", p.getProperty("AlbertsonsCompanies_URL")));
        footerLinkAndNavigation.put("Careers", new FooterLinkData(Careers, "TextOnTargetPage", p.getProperty("Careers_URL")));
        footerLinkAndNavigation.put("Suppliers", new FooterLinkData(Suppliers, "TextOnTargetPage", p.getProperty("Suppliers_URL")));
        footerLinkAndNavigation.put("For Employees", new FooterLinkData(ForEmployees, "TextOnTargetPage", p.getProperty("ForEmployees_URL")));
        footerLinkAndNavigation.put("Foundation", new FooterLinkData(Foundation, "TextOnTargetPage", p.getProperty("Foundation_URL")));
        footerLinkAndNavigation.put("Product Recalls", new FooterLinkData(ProductRecalls, "TextOnTargetPage", p.getProperty("ProductRecalls_URL")));
        footerLinkAndNavigation.put("Terms of Use", new FooterLinkData(TermsofUse, "TextOnTargetPage", p.getProperty("TermsofUse_URL")));
        footerLinkAndNavigation.put("Privacy Policy", new FooterLinkData(PrivacyPolicy, "TextOnTargetPage", p.getProperty("PrivacyPolicy_URL")));
        footerLinkAndNavigation.put("Accessibility Statement", new FooterLinkData(AccessibilityStatement, "TextOnTargetPage", p.getProperty("AccessibilityStatement_URL")));
        footerLinkAndNavigation.put("HIPAA Notice of Privacy Practices", new FooterLinkData(HIPAANoticeofPrivacyPractices, "TextOnTargetPage", p.getProperty("HIPAANoticeofPrivacyPractices_URL")));
        footerLinkAndNavigation.put("Anti-Human Trafficking", new FooterLinkData(AntiHumanTrafficking, "TextOnTargetPage", p.getProperty("AntiHumanTrafficking_URL")));
        footerLinkAndNavigation.put("Other Policies & Disclosures", new FooterLinkData(OtherPoliciesDisclosures, "TextOnTargetPage", p.getProperty("OtherPoliciesDisclosures_URL")));

        return safeway();
    }
}
