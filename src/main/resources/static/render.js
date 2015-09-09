var templates = {};

function render(template, model, url) {
    var compiledTemplate;
    if (templates[url] === undefined) {
        compiledTemplate = Handlebars.compile(template); 
        templates[url] = compiledTemplate;
    }
    else {
        compiledTemplate = templates[url];
    }
    return compiledTemplate(toJsonObject(model));
}

// Create a real JSON object from the model Map 
function toJsonObject(model) {
    var o = {}; 
    for (var k in model) { 
        // Convert Iterable like List to real JSON array
        if (model[k] instanceof Java.type("java.lang.Iterable")) {
            o[k] = Java.from(model[k]);
        }
        else {
            o[k] = model[k]; 
        } 
    }  
    return o;
}