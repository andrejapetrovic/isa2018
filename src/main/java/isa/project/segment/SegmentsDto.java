package isa.project.segment;

import java.util.List;

public class SegmentsDto {

	List<Segment> added;
	
	List<SegmentDto> updated;
	
	List<SegmentDto> deleted;

	public List<Segment> getAdded() {
		return added;
	}

	public void setAdded(List<Segment> added) {
		this.added = added;
	}

	public List<SegmentDto> getUpdated() {
		return updated;
	}

	public void setUpdated(List<SegmentDto> updated) {
		this.updated = updated;
	}

	public List<SegmentDto> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<SegmentDto> deleted) {
		this.deleted = deleted;
	}

	
	
	
}
