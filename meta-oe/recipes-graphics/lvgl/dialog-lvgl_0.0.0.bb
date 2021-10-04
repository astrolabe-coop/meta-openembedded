# SPDX-FileCopyrightText: Huawei Inc.
# SPDX-License-Identifier: MIT

# TODO: Pin upstream release
SRC_URI = "gitsm://git.ostc-eu.org/rzr/dialog-lvgl;destsuffix=${S};protocol=https;nobranch=1"
SRCREV = "sandbox/rzr/wayland/main"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ce0a84e5276f01364119c873b712c4f"

DEPENDS += "lvgl"
DEPENDS += "lv-drivers"

SUMMARY = "Basic UI utility to be used in scripts"
DESCRIPTION = "Inspired by ncurses' dialog, implemented using LVGL"
HOMEPAGE = "https://git.ostc-eu.org/rzr/dialog-lvgl/-/wikis/"

inherit pkgconfig
inherit features_check

REQUIRED_DISTRO_FEATURES = "wayland"

EXTRA_OEMAKE += "lvgl_driver=wayland"
EXTRA_OEMAKE += "sysroot=${RECIPE_SYSROOT}"
EXTRA_OEMAKE += "DESTDIR=${D}"

do_install() {
    oe_runmake install
    install -d  "${D}${bindir}"
    install -m755 "${S}/extra/scripts/${PN}-demo.sh" "${D}${bindir}/"
}
