/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author hp
 */
public class CandidateInfo {
    private String candidateId;
	private String candidateName;
	private String symbol;
	private String party;
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setParty(String party) {
		this.party=party;
	}
	public String getParty() {
		return party;
	}
	public CandidateInfo(String candidateId, String candidateName, String symbol, String party) {
		super();
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.symbol = symbol;
		this.party = party;
	}
	public CandidateInfo() {
		super();
	}
	@Override
	public String toString() {
		return "CandidateInfo [candidateId=" + candidateId + ", candidateName=" + candidateName + ", symbol=" + symbol
				+ ", party=" + party + "]";
}
}
