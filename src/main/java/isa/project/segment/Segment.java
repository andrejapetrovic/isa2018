package isa.project.segment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Segment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "segment_id")
	private Long id;

	private int rows, cols;

	public Long getId() {
		return id;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
