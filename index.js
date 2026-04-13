const report = require('multiple-cucumber-html-reporter');
report.generate({
    jsonDir: 'target/cucumber-report/',
    reportPath: 'target/cucumber-html-report/',
    metadata:{
        browser: {
            name: 'chrome',
            version: 'latest'
        },
        device: 'Windows',
        platform: {
            name: 'Windows'
        }
    },
    customData: {
        title: 'Run info',
        data: [
            {label: 'Project', value: 'Proyecto Selenium POM'},
            {label: 'Generated on', value: new Date().toLocaleString()}
        ]
    }
});
