<?php

namespace App\Config;

use CodeIgniter\Config\BaseConfig;

class Custom extends BaseConfig
{
    /**
     * @var string
     */
    public $AdminName;

    /**
     * @var string
     */
    public $AdminVersion;

    /**
     * @var string
     */
    public $FCMTopic;

    /**
     * @var string
     */
    public $SecurityCode;
}