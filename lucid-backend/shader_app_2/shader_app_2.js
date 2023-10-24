let artShader;

function preload(){
   artShader = loadShader('shader.vert', 'shader.frag'); 
}

function setup() {
  createCanvas(screen.width, screen.height, WEBGL);
}


function draw() {
  shader(artShader);
  artShader.setUniform("resolution", [float(screen.width) , float(screen.height)]);
  artShader.setUniform("time", (millis() / 1000.0));
  rect(0, 0, screen.width, screen.height);
}
