/**
 * 
 */
package com.crawlcmu.entities;

/**
 * This class is used as a container for maintaining the 
 * name and state of a checkbox
 * @author ishan
 *
 */
public class CheckBoxDataModel 
{
	private String name;
	 
	private boolean selected;
 
	public CheckBoxDataModel(String name) {
		super();
		this.name = name;
		this.selected = false;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public boolean isSelected() {
		return selected;
	}
 
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
