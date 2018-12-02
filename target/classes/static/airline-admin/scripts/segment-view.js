function SegmentView (segment, segNum, elementId) {
	this.rows = segment.rows;
	this.cols = segment.cols;
	this.travelClass = segment.travelClass;
	this.segmentId = segment.id;
	this.canvas = document.createElement('canvas');
	this.canvas.id = 'canvas'+segNum;
	$(elementId).append(this.canvas);
	
	this.d = 30;
	this.rx = 10;
	this.ry = 3;
	

	/*var mouseHandler =  function(e) {
			var d = this.d;
			var rx = this.rx;
			var ry = this.ry;
			
			  var r = this.getBoundingClientRect(),
		      x = e.clientX - r.left, //kursor,x
		      y = e.clientY - r.top;  // y
		  
			for (i=0; i<this.rows; i++) {
				for (j=0; j<this.cols; j++) {
					  if (x >= i*d+(i+1)*rx && x < i*d+(i+1)*rx+d && y >= j*d+(j+1)*ry && y < j*d+(j+1)*ry+d){
						  console.log(i + " " + j);
						  return [i, y];
					  }
				}
			}
		};
	
	this.canvas.addEventListener("click", mouseHandler);
	this.canvas.addEventListener("mousemove", mouseHandler);*/
}

//d = duzina stranice kvadrada, rx i ry razmak izmedju kvadrata po x i y
SegmentView.prototype.draw = function() {
	var d = this.d;
	var rx = this.rx;
	var ry = this.ry;
	this.canvas.width = (d+2*rx)*this.rows;
	this.canvas.height = (d+2*ry)*this.cols;
	var ctx = this.canvas.getContext('2d');
	
	var i,j;
	ctx.fillStyle='#E8EDF2';
	for (i=0; i<this.rows; i++) {
		for (j=0; j<this.cols; j++) {
			ctx.strokeRect(i*d+(i+1)*rx,j*d+(j+1)*ry,d,d);
			ctx.fillRect(i*d+(i+1)*rx,j*d+(j+1)*ry,d,d);
		}
	}
};

SegmentView.prototype.update = function(rows, cols) {
	this.canvas.getContext('2d').clearRect(0,0, this.canvas.width, this.canvas.height);
	this.rows = rows;
	this.cols = cols;
};
