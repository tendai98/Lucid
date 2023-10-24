let shaderObject;

function preload(){
    shaderObject = loadShader('shader.vert', 'shader.frag');
   
}

function setup() {
    createCanvas(screen.width, screen.height, WEBGL);
}


function draw() {
    shader(shaderObject);
    shaderObject.setUniform("resolution", [float(screen.width), float(screen.height)]);
    shaderObject.setUniform("time", (millis() / 1000.0));
    rect(0, 0, screen.width, screen.height);
}
