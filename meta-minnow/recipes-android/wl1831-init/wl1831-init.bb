SUMMARY = "Service and firmware to initialize WL1831 HCI UART"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
COMPATIBLE_MACHINE = "minnow"

SRC_URI = "https://git.ti.com/cgit/wilink8-bt/ti-bt-firmware/plain/TIInit_11.8.32.bts \
    file://wl1831-init.service"
SRC_URI[md5sum] = "665b7c25be21933acc30dda44cfcace6"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES:${PN} += "/etc/systemd/system/wl1831-init.service \ 
    /lib/firmware/ti-connectivity/TIInit_11.8.32.bts"

do_install() {
    install -d ${D}/etc/systemd/system/
    install -d ${D}/lib/firmware/ti-connectivity
    install -m 0755 ${WORKDIR}/wl1831-init.service ${D}/etc/systemd/system
    install -m 0755 ${WORKDIR}/TIInit_11.8.32.bts ${D}/lib/firmware/ti-connectivity
    
    install -d ${D}/etc/systemd/system/bluetooth.target.wants/
    install -d ${D}/etc/systemd/system/basic.target.wants/
    ln -s ../wl1831-init.service ${D}/etc/systemd/system/basic.target.wants/wl1831-init.service
}
