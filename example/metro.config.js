const {mergeConfig, getDefaultConfig} = require("@react-native/metro-config");
const path = require('path')
/**
 * Metro configuration
 * https://facebook.github.io/metro/docs/configuration
 *
 * @type {import('metro-config').MetroConfig}
 */
const config = {
  watchFolders: [
    path.resolve(__dirname, "..")
  ],
  resolver: {
    unstable_enableSymlinks: true
  }
};

module.exports = mergeConfig(getDefaultConfig(__dirname), config);
