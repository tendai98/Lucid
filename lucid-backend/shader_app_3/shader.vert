attribute vec3 aPosition;

void main(){
	vec4 position =  vec4(aPosition, 1.0);
	position.xy = position.xy * 2.0 - 1.0;
	gl_Position = position;
}
