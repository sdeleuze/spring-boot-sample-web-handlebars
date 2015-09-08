var templates = {};

function render(template, model) {

    // Create a real Javascript Object from the model Map 
    var data = {}; 
    for (var k in model) { 
        // Convert Java Iterable and List to real Javascript arrays 
        if (model[k] instanceof Java.type("java.lang.Iterable")) {
            data[k] = Java.from(model[k]);
        } else {
            data[k] = model[k]; 
        } 
    }  

    var compiledTemplate;
    if (templates[template] === undefined) {
        compiledTemplate = Handlebars.compile(template); 
        templates[template] = compiledTemplate;
    }
    else {
        compiledTemplate = templates[template];
    }

    return compiledTemplate(data);
}