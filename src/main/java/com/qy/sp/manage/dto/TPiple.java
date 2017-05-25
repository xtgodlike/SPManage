package com.qy.sp.manage.dto;

import java.util.Date;

public class TPiple{
    private String pipleId;

	private String pipleName;

	private String supplierId;

	private Integer opStatus;

	private String contractId;

	private String pipleUrlA;

	private String pipleUrlB;

	private String notifyUrlA;

	private String notifyUrlB;

	private String channelUrlA;

	private String channelUrlB;

	private String pipleDoc;

	private String channelDoc;

	private String pipleAuthA;

	private String pipleAuthB;

	private String pipleAuthC;

	private String pipleAuthD;

	private Integer hostId;

	private Integer codeType;

	private Integer pipleType;

	private Integer calcType;

	private String pipleNumber;

	private Date createTime;

	private Date modTime;
	private String pluginId;  // 插件ID
	private String pluginVersion; // 插件版本
	private String testPluginId;  // 测试插件ID
	private Integer pluginType; // 插件类型
	private double settlementRatio;
	
	private String supplierName; // 供应商名
    private String pipleDocName; // 通道文档名
    private String channelDocName; // 渠道文档名
    private String hostName; // 运营商名
    private String pluginName; // 插件名
    private String pipleTypeDesc; // 通道类型描述
    private String calcTypeDesc; // 结算类型描述
    private String codeTypeDesc; // 代码类型描述
    private String pluginTypeDesc; // 插件类型描述
	public String getPipleId() {
		return pipleId;
	}

	public void setPipleId(String pipleId) {
		this.pipleId = pipleId == null ? null : pipleId.trim();
	}

	public String getPipleName() {
		return pipleName;
	}

	public void setPipleName(String pipleName) {
		this.pipleName = pipleName == null ? null : pipleName.trim();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public Integer getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(Integer opStatus) {
		this.opStatus = opStatus;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId == null ? null : contractId.trim();
	}

	public String getPipleUrlA() {
		return pipleUrlA;
	}

	public void setPipleUrlA(String pipleUrlA) {
		this.pipleUrlA = pipleUrlA == null ? null : pipleUrlA.trim();
	}

	public String getPipleUrlB() {
		return pipleUrlB;
	}

	public void setPipleUrlB(String pipleUrlB) {
		this.pipleUrlB = pipleUrlB == null ? null : pipleUrlB.trim();
	}

	public String getNotifyUrlA() {
		return notifyUrlA;
	}

	public void setNotifyUrlA(String notifyUrlA) {
		this.notifyUrlA = notifyUrlA == null ? null : notifyUrlA.trim();
	}

	public String getNotifyUrlB() {
		return notifyUrlB;
	}

	public void setNotifyUrlB(String notifyUrlB) {
		this.notifyUrlB = notifyUrlB == null ? null : notifyUrlB.trim();
	}

	public String getChannelUrlA() {
		return channelUrlA;
	}

	public void setChannelUrlA(String channelUrlA) {
		this.channelUrlA = channelUrlA == null ? null : channelUrlA.trim();
	}

	public String getChannelUrlB() {
		return channelUrlB;
	}

	public void setChannelUrlB(String channelUrlB) {
		this.channelUrlB = channelUrlB == null ? null : channelUrlB.trim();
	}

	public String getPipleDoc() {
		return pipleDoc;
	}

	public void setPipleDoc(String pipleDoc) {
		this.pipleDoc = pipleDoc == null ? null : pipleDoc.trim();
	}

	public String getChannelDoc() {
		return channelDoc;
	}

	public void setChannelDoc(String channelDoc) {
		this.channelDoc = channelDoc == null ? null : channelDoc.trim();
	}

	public String getPipleAuthA() {
		return pipleAuthA;
	}

	public void setPipleAuthA(String pipleAuthA) {
		this.pipleAuthA = pipleAuthA == null ? null : pipleAuthA.trim();
	}

	public String getPipleAuthB() {
		return pipleAuthB;
	}

	public void setPipleAuthB(String pipleAuthB) {
		this.pipleAuthB = pipleAuthB == null ? null : pipleAuthB.trim();
	}

	public String getPipleAuthC() {
		return pipleAuthC;
	}

	public void setPipleAuthC(String pipleAuthC) {
		this.pipleAuthC = pipleAuthC == null ? null : pipleAuthC.trim();
	}

	public String getPipleAuthD() {
		return pipleAuthD;
	}

	public void setPipleAuthD(String pipleAuthD) {
		this.pipleAuthD = pipleAuthD == null ? null : pipleAuthD.trim();
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}

	public Integer getPipleType() {
		return pipleType;
	}

	public void setPipleType(Integer pipleType) {
		this.pipleType = pipleType;
	}

	public Integer getCalcType() {
		return calcType;
	}

	public void setCalcType(Integer calcType) {
		this.calcType = calcType;
	}

	public String getPipleNumber() {
		return pipleNumber;
	}

	public void setPipleNumber(String pipleNumber) {
		this.pipleNumber = pipleNumber == null ? null : pipleNumber.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}
	
    public String getPipleDocName() {
		return pipleDocName;
	}

	public void setPipleDocName(String pipleDocName) {
		this.pipleDocName = pipleDocName;
	}

	public String getChannelDocName() {
		return channelDocName;
	}

	public void setChannelDocName(String channelDocName) {
		this.channelDocName = channelDocName;
	}

    public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPipleTypeDesc() {
		return pipleTypeDesc;
	}

	public void setPipleTypeDesc(String pipleTypeDesc) {
		this.pipleTypeDesc = pipleTypeDesc;
	}

	public String getCalcTypeDesc() {
		return calcTypeDesc;
	}

	public void setCalcTypeDesc(String calcTypeDesc) {
		this.calcTypeDesc = calcTypeDesc;
	}

	public String getCodeTypeDesc() {
		return codeTypeDesc;
	}

	public void setCodeTypeDesc(String codeTypeDesc) {
		this.codeTypeDesc = codeTypeDesc;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getPluginVersion() {
		return pluginVersion;
	}

	public void setPluginVersion(String pluginVersion) {
		this.pluginVersion = pluginVersion;
	}

	public double getSettlementRatio() {
		return settlementRatio;
	}

	public void setSettlementRatio(double settlementRatio) {
		this.settlementRatio = settlementRatio;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public String getTestPluginId() {
		return testPluginId;
	}

	public Integer getPluginType() {
		return pluginType;
	}

	public void setPluginType(Integer pluginType) {
		this.pluginType = pluginType;
	}

	public void setTestPluginId(String testPluginId) {
		this.testPluginId = testPluginId;
	}


	public String getPluginTypeDesc() {
		return pluginTypeDesc;
	}

	public void setPluginTypeDesc(String pluginTypeDesc) {
		this.pluginTypeDesc = pluginTypeDesc;
	}

}