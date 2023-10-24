precision mediump float;


uniform vec2 resolution;
uniform float time;


void main(){
	vec2 uv = ((gl_FragCoord.xy / resolution) - 1.5) * 2.0;
	uv.x *= resolution.x / resolution.y;
	uv.y -= 0.5;

	float d = length(uv);
	vec4 colorOutput = vec4(1.0, 2.0, 3.0, 1.0);

	d = sin(d * 8.0 + time*3.0) / 9.0;
	d = abs(d);
	d = smoothstep(0.0, 0.1, d);

	d = 0.35 / d;
	colorOutput *= d;

	gl_FragColor = colorOutput;
}
