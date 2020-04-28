Pod::Spec.new do |s|
    s.name         = 'RNChat'
    s.version      = '1.0.27'
    s.summary      = 'RNChat'
    s.description  = <<-DESC
                    Reactive Chat Library
                     DESC
    s.homepage     = 'https://github.com/codlab/react-native-chat'
    s.license      = 'Apache 2.0'
    s.author       = { 'codlab' => 'codlabtech@gmail.com' }
    s.platform     = :ios, '9.0'
    s.source       = { :git => 'https://github.com/codlab/react-native-chat.git', :tag => 'master' }
    s.requires_arc = true
  
    s.dependency 'React'
    s.source_files  = "ios/*.{h,m}"
    s.public_header_files = 'ios/RNChat.h'
end