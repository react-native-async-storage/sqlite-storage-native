require "json"

info = JSON.parse(File.read(File.join(__dir__, "package_info.json")))

podspecName   = info["darwin"]["podspec_name"]
frameworkName = info["darwin"]["xcframework_name"]

Pod::Spec.new do |spec|
  spec.name         = podspecName
  spec.version      = info["version"]
  spec.summary      = info["description"]
  spec.homepage     = info["homepage"]
  spec.license      = info["license"]["type"]
  spec.author       = info["author"]
  spec.source       = { :git => info["repository"], :tag => "#{spec.version}" }

  spec.platforms    = { :ios => "13.0" }

  spec.vendored_frameworks = "#{podspecName}/#{frameworkName}.xcframework"
  spec.source_files = "#{podspecName}/src/**/*.{swift}"

  spec.dependency "KMPNativeCoroutinesAsync", "1.0.0-ALPHA-24"

end
