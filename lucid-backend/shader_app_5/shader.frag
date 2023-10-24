precision mediump float;

uniform vec2 resolution;
uniform float time;

vec4 palette(float t){
	vec4 a = vec4(0.5, 0.5, 0.5, 1.0);
	vec4 b = vec4(0.5, 0.5, 0.5, 1.0);
	vec4 c = vec4(1.0, 1.0, 1.0, 1.0);
	vec4 d = vec4(0.263, 0.416, 0.557, 1.0);

	return a + b * cos(6.28318 * (c*t+d));
}

void main(){

	vec2 uv = ((gl_FragCoord.xy / resolution) - 1.5) * 2.0;
	uv.x *= resolution.x / resolution.y;
	uv.y -= 0.5;

	float d = length(uv);
	vec4 colorOutput = palette(d + time);

	d = sin(d * 8.0 + time) / 8.0;
	d = abs(d);

	d = 0.05 / d;
	colorOutput *= d;

	gl_FragColor = colorOutput;	

}
